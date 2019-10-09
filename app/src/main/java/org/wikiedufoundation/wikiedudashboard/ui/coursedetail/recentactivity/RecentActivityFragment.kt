package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view

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
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.RecentActivityRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityContract
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RetrofitRecentActivityProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivityResponse
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

class RecentActivityFragment : Fragment(), RecentActivityContract.View {

    private var tvNoActivity: TextView? = null
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    private var recentActivityPresenter: RecentActivityContract.Presenter? = null
    private var recentActivityRecyclerAdapter: RecentActivityRecyclerAdapter? = null
    private var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recent_activity, container, false)

        url = arguments?.getString("url", null)
        val context: Context? = context
        recyclerView = view.findViewById(R.id.rv_edited_articles_list)
        progressBar = view.findViewById(R.id.progress_bar)
        tvNoActivity = view.findViewById(R.id.tv_no_activity)

        recentActivityPresenter = RecentActivityPresenterImpl(this, RetrofitRecentActivityProvider())

        recentActivityRecyclerAdapter = RecentActivityRecyclerAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = recentActivityRecyclerAdapter

        url?.let { recentActivityPresenter?.requestRecentActivity(it) }
        return view
    }

    override fun setData(data: RecentActivityResponse) {
        Timber.d(data.toString())
        if (data.course.revisions.isNotEmpty()) {
            recyclerView?.visibility = View.VISIBLE
            recentActivityRecyclerAdapter?.setData(data.course.revisions)
            recentActivityRecyclerAdapter?.notifyDataSetChanged()
            tvNoActivity?.visibility = View.GONE
        } else {
            recyclerView?.visibility = View.GONE
            tvNoActivity?.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
    }
}
