package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_campaign_list.*
import kotlinx.android.synthetic.main.fragment_recent_activity.*
import kotlinx.android.synthetic.main.fragment_recent_activity.progressBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.RecentActivityRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.viewmodel.RecentActivityViewModel
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] for recent activities
 * ***/
class RecentActivityFragment : Fragment() {

    private lateinit var recentActivityRecyclerAdapter: RecentActivityRecyclerAdapter

    private var url: String? = null

    private val recentActivityViewModel by viewModel<RecentActivityViewModel> { parametersOf(url) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recent_activity, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null)
        val context: Context? = context

        recentActivityRecyclerAdapter = RecentActivityRecyclerAdapter(R.layout.item_rv_recent_activity)

        initializeRecyclerView()
        setData()
        initialzeProgressBar()
        initialzeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerEditedArticlesList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recentActivityRecyclerAdapter
        }
    }

    /**
     *   This set the data on the view from the viewmodel
     */
    fun setData() {
        recentActivityViewModel.recentList.observe(this, Observer {
            Timber.d("recent $it.size")
            if (it.isNotEmpty()) {
                recyclerEditedArticlesList?.visibility = View.VISIBLE
                recentActivityRecyclerAdapter.setData(it)
                textViewNoActivity?.visibility = View.GONE
            } else {
                recyclerEditedArticlesList?.visibility = View.GONE
                textViewNoActivity?.visibility = View.VISIBLE
            }
        })
    }

    /**
     *   This shows the progressbar
     */
    fun initialzeProgressBar() {
        recentActivityViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    /**
     *   This shows the message
     */
    fun initialzeToaster() {
        recentActivityViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            context?.showToast(message)
        })
    }
}
