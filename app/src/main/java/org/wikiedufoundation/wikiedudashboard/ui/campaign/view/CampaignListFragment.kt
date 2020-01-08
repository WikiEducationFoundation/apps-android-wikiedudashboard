package org.wikiedufoundation.wikiedudashboard.ui.campaign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_campaign_list.*
import org.koin.android.ext.android.inject
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CampaignListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.CourseListViewModel
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.ActiveCampaignViewModel
import org.wikiedufoundation.wikiedudashboard.util.filterOrEmptyList
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CampaignListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CampaignListFragment : Fragment() {
    private val activeCampaignViewModel: ActiveCampaignViewModel by inject()

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

        initializeRecyclerView()
        sharedPrefs.cookies?.let { (activeCampaignViewModel.fetchCampaignList(it)) }
        setData()
        showProgressBar()
        showMessage()
    }

    /**
     *   This initializes the recyclerview
     */

    fun initializeRecyclerView() {
        recyclerCampaignList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = campaignListRecyclerAdapter
        }
    }

    /**
     *   This sets the data to be displayed on the recyclerview based on available data
     */
    fun setData() {
        activeCampaignViewModel.data.observe(this, androidx.lifecycle.Observer {
            Timber.d("hello", it.toString())
            if (it.isNotEmpty()) {
                recyclerCampaignList?.visibility = View.VISIBLE
                campaignListRecyclerAdapter.setData(it)
                campaignListRecyclerAdapter.notifyDataSetChanged()
                textViewNoCampaigns?.visibility = View.GONE

            } else {
                recyclerCampaignList?.visibility = View.GONE
                textViewNoCampaigns?.visibility = View.VISIBLE
            }
        })
    }

    /**
     *   This shows the progressbar
     */
    fun showProgressBar() {
        activeCampaignViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            it?.let {
                if (it) progressBar.visibility = View.VISIBLE else View.GONE
            }
        })
    }

    /**
     *   This shows the message
     */
    fun showMessage() {
        activeCampaignViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            Toast.makeText(context,  it?.message.toString(), Toast.LENGTH_LONG).show()
        })
    }

    /**
     *   This performs search
     */
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