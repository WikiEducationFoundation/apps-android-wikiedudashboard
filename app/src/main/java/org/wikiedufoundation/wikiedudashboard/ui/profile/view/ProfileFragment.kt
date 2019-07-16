package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.ProfilePresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RetrofitProfileProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import org.wikiedufoundation.wikiedudashboard.util.ViewUtils
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ProfileContract.View {
    override fun setData(data: ProfileResponse) {
        setTabs(data)
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

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var progressBar: ProgressBar? = null

    private var sharedPrefs: SharedPrefs? = null
    private var presenter: ProfileContract.Presenter? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_training, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        progressBar = view.findViewById(R.id.progressBar)
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager!!.adapter = viewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
//        context = getContext()
        sharedPrefs = SharedPrefs(getContext())
        toolbar!!.setNavigationOnClickListener { activity!!.onBackPressed() }
        presenter = ProfilePresenterImpl(this, RetrofitProfileProvider())
        presenter!!.requestProfile(sharedPrefs?.cookies!!, sharedPrefs?.userName!!)
        return view
    }

    private fun setTabs(data: ProfileResponse) {
        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("Contribution Statistics")
        fragmentList.add(ProfileStatsFragment())
        titleList.add("Course Details")
        fragmentList.add(ProfileCourseListFragment.newInstance(data))
        titleList.add("Recent Uploads")
        val courseUploadList = CourseUploadList(data.uploads!!)
        fragmentList.add(CourseUploadsFragment.newInstance(2, "", courseUploadList))

        viewPagerAdapter!!.setTabData(fragmentList, titleList)
        viewPagerAdapter!!.notifyDataSetChanged()
    }


}