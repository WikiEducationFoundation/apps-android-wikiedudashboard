package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recent_activity.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.RecentActivityRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.showSnackbar
import timber.log.Timber

/**
 * A simple [Fragment] for recent activities
 * ***/
class RecentActivityFragment : Fragment(), RecentActivityContract.View {

    private val retrofitRecentActivityProvider: RetrofitRecentActivityProvider by inject()
    private val recentActivityPresenter: RecentActivityContract.Presenter by inject {
        parametersOf(this, retrofitRecentActivityProvider)
    }

    private lateinit var recentActivityRecyclerAdapter: RecentActivityRecyclerAdapter

    private var url: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recent_activity, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null)
        val context: Context? = context

        recentActivityRecyclerAdapter = RecentActivityRecyclerAdapter(R.layout.item_rv_recent_activity)

        recyclerEditedArticlesList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recentActivityRecyclerAdapter
        }

        url?.let { recentActivityPresenter.requestRecentActivity(it) }
    }

    override fun setData(data: RecentActivityResponse) {
        Timber.d(data.toString())
        if (data.course.revisions.isNotEmpty()) {
            recyclerEditedArticlesList?.visibility = View.VISIBLE
            recentActivityRecyclerAdapter.setData(data.course.revisions)
            recentActivityRecyclerAdapter.notifyDataSetChanged()
            textViewNoActivity?.visibility = View.GONE
        } else {
            recyclerEditedArticlesList?.visibility = View.GONE
            textViewNoActivity?.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        progressBar?.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showMessage(message: String) {
        view?.showSnackbar(message)
    }
}
