package org.wikiedufoundation.wikiedudashboard.ui.campaign.view

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
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CampaignListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListContract
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.campaign.RetrofitCampaignListProvider
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.util.filterOrEmptyList
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CampaignListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CampaignListFragment : Fragment(), CampaignListContract.View {

    private val retrofitCampaignListProvider: RetrofitCampaignListProvider by inject()

    private var mParam1: String? = null
    private var mParam2: String? = null
    private var sharedPrefs: SharedPrefs? = null

    private lateinit var tvNoCampaigns: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var campaignListPresenter: CampaignListContract.Presenter
    private lateinit var campaignListRecyclerAdapter: CampaignListRecyclerAdapter

    private var campaignList: List<CampaignListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_campaign_list, container, false)
        recyclerView = view.findViewById(R.id.rv_campaign_list)
        progressBar = view.findViewById(R.id.progressBar)
        tvNoCampaigns = view.findViewById(R.id.tv_no_campaigns)

        sharedPrefs = context?.let { SharedPrefs(it) }
        campaignListPresenter = CampaignListPresenterImpl(this, retrofitCampaignListProvider)

        campaignListRecyclerAdapter = CampaignListRecyclerAdapter(R.layout.item_rv_campaign_list) {
//                        openCourseDetail(it)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = campaignListRecyclerAdapter
        }

        sharedPrefs?.cookies?.let { campaignListPresenter.requestCampaignList(it) }
        return view
    }

    override fun setData(data: ExploreCampaignsResponse) {
        Timber.d(data.toString())
        if (data.campaigns.isNotEmpty()) {
            campaignList = data.campaigns
            recyclerView.visibility = View.VISIBLE
            campaignListRecyclerAdapter.setData(data.campaigns)
            campaignListRecyclerAdapter.notifyDataSetChanged()
            tvNoCampaigns.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            tvNoCampaigns.visibility = View.VISIBLE
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

    fun updateSearchQuery(query: String) {
        Timber.d(query)

        val campaignQueryFilter = campaignList.filterOrEmptyList {
            it.title.toLowerCase(Locale.getDefault())
                    .contains(query.toLowerCase(Locale.getDefault()))
        }

        campaignListRecyclerAdapter.setData(campaignQueryFilter)
        campaignListRecyclerAdapter.notifyDataSetChanged()
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): CampaignListFragment {
            val fragment = CampaignListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}