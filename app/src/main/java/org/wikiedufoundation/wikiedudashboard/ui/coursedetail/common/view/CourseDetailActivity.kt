package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.CourseArticlesEditedFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.RecentActivityFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.RetrofitCourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.home.CourseHomeFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.timeline.CourseTimelineFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view.StudentListFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import java.util.*

class CourseDetailActivity : AppCompatActivity(), CourseDetailView {

    private var courseDetailPresenter: CourseDetailPresenter? = null
    private var url: String? = null
    private var enrolled: Boolean? = null

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var progressBar: ProgressBar? = null

    private var context: Context? = null
    private var sharedPrefs: SharedPrefs? = null
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var courseHomeFragment: CourseHomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)
        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        progressBar = findViewById(R.id.progressBar)

        url = intent.getStringExtra("url")
        enrolled = intent.getBooleanExtra("enrolled", false)

        context = this
        sharedPrefs = SharedPrefs(context)

        val action = intent.action
        val data = intent.dataString
        if (Intent.ACTION_VIEW == action && data != null) {

            url = data.substring(data.lastIndexOf("courses/") + 8)
            if (url!!.contains("?enroll")) {
                val enrollParam = url!!.substring(url!!.lastIndexOf("?enroll") + 7)
                url = url!!.substring(0, url!!.lastIndexOf("?enroll"))
            }
        }
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = viewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        toolbar!!.setNavigationOnClickListener { onBackPressed() }
        courseDetailPresenter = CourseDetailPresenterImpl(this, RetrofitCourseDetailProvider())
        courseDetailPresenter!!.requestCourseDetail(url!!)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: CourseDetail) {
        toolbar!!.title = data.title
        courseHomeFragment = CourseHomeFragment.newInstance(data)
        setTabs()
    }

    private fun setTabs() {
        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("Home")
        fragmentList.add(courseHomeFragment)
//        if (sharedPrefs!!.cookies == sharedPrefs!!.wikiEduDashboardCookies) {
//            titleList.add("Timeline")
//            fragmentList.add(CourseTimelineFragment())
//        }
        titleList.add("Students")
        val studentListFragment = StudentListFragment()
        val bundle = Bundle()
        bundle.putString("url", url)
        titleList.add("Article")
        studentListFragment.arguments = bundle
        fragmentList.add(studentListFragment)
        val courseArticlesEditedFragment = CourseArticlesEditedFragment()
        val bundle2 = Bundle()
        bundle2.putString("url", url)
        courseArticlesEditedFragment.arguments = bundle2
        fragmentList.add(courseArticlesEditedFragment)
        titleList.add("Uploads")
        fragmentList.add(CourseUploadsFragment.newInstance(type = 1, courseDetail = url!!, courseUploads = null))
        titleList.add("Activity")
        val recentActivityFragment = RecentActivityFragment()
        val bundle3 = Bundle()
        bundle3.putString("url", url)
        recentActivityFragment.arguments = bundle3
        fragmentList.add(recentActivityFragment)

        viewPagerAdapter!!.setTabData(fragmentList, titleList)
        viewPagerAdapter!!.notifyDataSetChanged()
    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }
}
