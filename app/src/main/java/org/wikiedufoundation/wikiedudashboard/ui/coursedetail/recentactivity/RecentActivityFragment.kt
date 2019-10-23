package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.RecentActivityRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] for recent activities
 * ***/
class RecentActivityFragment : Fragment(), RecentActivityContract.View {

    private val retrofitRecentActivityProvider: RetrofitRecentActivityProvider by inject()
    private val recentActivityPresenter: RecentActivityContract.Presenter by inject {
        parametersOf(this, retrofitRecentActivityProvider)
    }

    private lateinit var tvNoActivity: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var recentActivityRecyclerAdapter: RecentActivityRecyclerAdapter

    private var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recent_activity, container, false)

        url = arguments?.getString("url", null)
        val context: Context? = context
        recyclerView = view.findViewById(R.id.rv_edited_articles_list)
        progressBar = view.findViewById(R.id.progress_bar)
        tvNoActivity = view.findViewById(R.id.tv_no_activity)

        recentActivityRecyclerAdapter = RecentActivityRecyclerAdapter(R.layout.item_rv_recent_activity)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recentActivityRecyclerAdapter
        }

        url?.let { recentActivityPresenter.requestRecentActivity(it) }
        return view
    }

    override fun setData(data: RecentActivityResponse) {
        Timber.d(data.toString())
        if (data.course.revisions.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            recentActivityRecyclerAdapter.setData(data.course.revisions)
            recentActivityRecyclerAdapter.notifyDataSetChanged()
            tvNoActivity.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            tvNoActivity.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
    }
}
