package org.wikiedufoundation.wikiedudashboard.ui.campaign.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_campaign_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CampaignListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel.ActiveCampaignViewModel
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.view.CampaignDetailActivity
import org.wikiedufoundation.wikiedudashboard.util.filterOrEmptyList
import org.wikiedufoundation.wikiedudashboard.util.showSnackbar
import timber.log.Timber
import java.util.Locale

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CampaignListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CampaignListFragment : Fragment() {
    private val sharedPrefs: SharedPrefs by inject()
    private var mParam1: String? = null
    private var mParam2: String? = null
    private lateinit var campaignListRecyclerAdapter: CampaignListRecyclerAdapter
    private val activeCampaignViewModel by viewModel<ActiveCampaignViewModel> { parametersOf(sharedPrefs.cookies) }
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
    ): View? = inflater.inflate(R.layout.fragment_campaign_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        campaignListRecyclerAdapter = CampaignListRecyclerAdapter(R.layout.item_rv_campaign_list) {
            openCampaignDetail(it)
        }
        initializeRecyclerView()
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerCampaignList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = campaignListRecyclerAdapter
        }
    }

    private fun setData() {
        activeCampaignViewModel.data.observe(
            this,
            androidx.lifecycle.Observer {
                Timber.d(it.toString())
                if (it.isNotEmpty()) {
                    recyclerCampaignList?.visibility = View.VISIBLE
                    campaignListRecyclerAdapter.setData(it)
                    textViewNoCampaigns?.visibility = View.GONE
                } else {
                    recyclerCampaignList?.visibility = View.GONE
                    textViewNoCampaigns?.visibility = View.VISIBLE
                }
            }
        )
    }

    private fun initializeProgressBar() {
        activeCampaignViewModel.progressbar.observe(
            this,
            androidx.lifecycle.Observer {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        )
    }

    private fun initializeToaster() {
        activeCampaignViewModel.showMsg.observe(
            this,
            androidx.lifecycle.Observer {
                val message = it?.showMsg
                message?.let { msg -> view?.showSnackbar(msg) }
            }
        )
    }

    private fun openCampaignDetail(slug: String) {
        val i = Intent(context, CampaignDetailActivity::class.java)
        i.putExtra("url", slug)
        i.putExtra("enrolled", false)
        startActivity(i)
    }

    /**
     *   This performs search
     */
    fun updateSearchQuery(query: String) {
        Timber.d(query)

        val campaignQueryFilter = activeCampaignViewModel.data.value.filterOrEmptyList {
            it.title.toLowerCase(Locale.getDefault())
                .contains(query.toLowerCase(Locale.getDefault()))
        }

        campaignListRecyclerAdapter.setData(campaignQueryFilter)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String) = CampaignListFragment().apply {
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            this.arguments = args
        }
    }
}
