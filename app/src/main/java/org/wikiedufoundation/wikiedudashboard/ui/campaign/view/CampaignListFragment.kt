package org.wikiedufoundation.wikiedudashboard.ui.campaign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_campaign_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CampaignListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListContract
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
    private val campaignListPresenter: CampaignListContract.Presenter by inject {
        parametersOf(this, retrofitCampaignListProvider)
    }
    private val sharedPrefs: SharedPrefs by inject()

    private var mParam1: String? = null
    private var mParam2: String? = null

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
        return inflater.inflate(R.layout.fragment_campaign_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        campaignListRecyclerAdapter = CampaignListRecyclerAdapter(R.layout.item_rv_campaign_list) {
            //                        openCourseDetail(it)
        }

        recyclerCampaignList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = campaignListRecyclerAdapter
        }

        sharedPrefs.cookies?.let { campaignListPresenter.requestCampaignList(it) }
    }

    override fun setData(data: ExploreCampaignsResponse) {
        if (isAdded) {
            Timber.d(data.toString())
            if (data.campaigns.isNotEmpty()) {
                campaignList = data.campaigns
                recyclerCampaignList.visibility = View.VISIBLE
                campaignListRecyclerAdapter.setData(data.campaigns)
                campaignListRecyclerAdapter.notifyDataSetChanged()
                textViewNoCampaigns.visibility = View.GONE
            } else {
                recyclerCampaignList.visibility = View.GONE
                textViewNoCampaigns.visibility = View.VISIBLE
            }
        }
    }

    override fun showProgressBar(show: Boolean) {
        if (isAdded) {
            progressBar?.visibility = if (show) {
                View.VISIBLE
            } else {
                View.GONE
            }
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