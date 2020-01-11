package org.wikiedufoundation.wikiedudashboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_explore.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.campaign.view.CampaignListFragment
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListFragment
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private var campaignListFragment = CampaignListFragment()
    private var courseListFragment = CourseListFragment()

    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        val view = inflater.inflate(R.layout.fragment_explore, container, false)
        val viewPager = view.viewPager
        val tabLayout = view.tabLayout

        setTabs(tabLayout, viewPager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.menu_explore)
        toolbar.setOnMenuItemClickListener(this)
    }

    private fun setTabs(tabLayout: TabLayout, viewPager: ViewPager) {
        val fragmentList = listOf<Fragment>(campaignListFragment, courseListFragment)
        val titleList = listOf("Active Campaigns", "Active Courses")

        viewPager.apply {
            adapter = ViewPagerAdapter(childFragmentManager, fragmentList, titleList)
        }
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val searchView = item!!.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.isIconified = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Timber.d(query)
                campaignListFragment.updateSearchQuery(query)
                courseListFragment.updateSearchQuery(query)

                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                item.collapseActionView()
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                Timber.d(query)
                campaignListFragment.updateSearchQuery(query)
                courseListFragment.updateSearchQuery(query)
                return false
            }
        })
        return true
    }

    companion object {
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
        fun newInstance(param1: String, param2: String): ExploreFragment {
            val fragment = ExploreFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
} // Required empty public constructor
