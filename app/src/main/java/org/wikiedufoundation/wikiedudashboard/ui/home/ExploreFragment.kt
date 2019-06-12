package org.wikiedufoundation.wikiedudashboard.ui.home

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.campaign.view.CampaignListFragment
import org.wikiedufoundation.wikiedudashboard.ui.course_list.view.CourseListFragment
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter

import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_explore.view.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    var tabLayout: TabLayout? = null
    var viewPager : ViewPager? = null
    var viewPagerAdapter: ViewPagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)
        viewPager = view.viewPager
        tabLayout = view.tabLayout
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager!!.adapter = viewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        setTabs()
        return view
    }

    private fun setTabs() {
        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("Active Campaigns")
        fragmentList.add(CampaignListFragment())
        titleList.add("Active Courses")
        fragmentList.add(CourseListFragment())
        viewPagerAdapter!!.setTabData(fragmentList, titleList)
        viewPagerAdapter!!.notifyDataSetChanged()
    }

    companion object {
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
        fun newInstance(param1: String, param2: String): ExploreFragment {
            val fragment = ExploreFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
