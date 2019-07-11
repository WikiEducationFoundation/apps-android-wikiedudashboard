package org.wikiedufoundation.wikiedudashboard.ui.campaign.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CampaignListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListContract
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.campaign.RetrofitCampaignListProvider
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ExploreCampaignsResponse
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.util.ViewUtils

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CampaignListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CampaignListFragment : Fragment(), CampaignListContract.View {

    private var TAG : String = "CampaignListFragment"
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var tvNoCampaigns: TextView? = null
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    private var sharedPrefs: SharedPrefs? = null
    private var campaignListPresenter: CampaignListContract.Presenter? = null
    private var campaignListRecyclerAdapter: CampaignListRecyclerAdapter? = null
    private var campaignList: List<CampaignListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
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

        val context: Context? = context
        sharedPrefs = SharedPrefs(context)
        campaignListPresenter = CampaignListPresenterImpl(this, RetrofitCampaignListProvider())
        campaignListRecyclerAdapter = CampaignListRecyclerAdapter(context!!, this)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = campaignListRecyclerAdapter

        campaignListPresenter!!.requestCampaignList(sharedPrefs!!.cookies!!)
        return view
    }

    override fun setData(data: ExploreCampaignsResponse) {
        Log.d(TAG, data.toString())
        if (data.campaigns.isNotEmpty()) {
            campaignList = data.campaigns
            recyclerView!!.visibility = View.VISIBLE
            campaignListRecyclerAdapter!!.setData(data.campaigns)
            campaignListRecyclerAdapter!!.notifyDataSetChanged()
            tvNoCampaigns!!.visibility = View.GONE
        } else {
            recyclerView!!.visibility = View.GONE
            tvNoCampaigns!!.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        ViewUtils.showToast(context!!, message)
    }

    fun updateSearchQuery(query: String) {
        Log.d("CampaignListFragment: ", query)
        val filteredCampaignList: ArrayList<CampaignListData>? = ArrayList()
        for (campaign in campaignList) {
            if (campaign.title.toLowerCase().contains(query.toLowerCase())) {
                filteredCampaignList!!.add(campaign)
            }
        }
        campaignListRecyclerAdapter!!.setData(filteredCampaignList!!)
        campaignListRecyclerAdapter!!.notifyDataSetChanged()
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