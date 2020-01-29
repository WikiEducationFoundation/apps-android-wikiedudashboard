package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_course_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.CourseArticlesEditedFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.home.CourseHomeFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.viewmodel.CourseDetailViewModel
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view.StudentListFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import org.wikiedufoundation.wikiedudashboard.util.showToast

/**
 * Activity view for course detail
 * ***/
class CourseDetailActivity : AppCompatActivity(){

    private var enrolled = false
    private lateinit var url: String
    private lateinit var courseHomeFragment: CourseHomeFragment
    private val courseDetailViewModel by viewModel<CourseDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        url = intent.getStringExtra("url")
        enrolled = intent.getBooleanExtra("enrolled", false)

        val action = intent.action
        val data = intent.dataString
        if (Intent.ACTION_VIEW == action && data != null) {

            url = data.substring(data.lastIndexOf("courses/") + 8)
            val urlExists = url.contains("?enroll")
            if (urlExists) {
                url = url.lastIndexOf("?enroll").let { url.substring(0, it) }
            }
        }
        url?.let { courseDetailViewModel.requestCourseDetail(it) }
        tabLayout.setupWithViewPager(viewPager)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    /**
     * Set course detail tabs
     * ***/
    private fun setTabs() {
        val studentListFragment = StudentListFragment()
        val bundle = Bundle()
        bundle.putString("url", url)
        studentListFragment.arguments = bundle

        val courseArticlesEditedFragment = CourseArticlesEditedFragment()
        val bundle2 = Bundle()
        bundle2.putString("url", url)
        courseArticlesEditedFragment.arguments = bundle2

        val recentActivityFragment = RecentActivityFragment()
        val bundle3 = Bundle()
        bundle3.putString("url", url)
        recentActivityFragment.arguments = bundle3

        val fragmentList = listOf(courseHomeFragment,
                studentListFragment,
                courseArticlesEditedFragment,
                url.let { CourseUploadsFragment.newInstance(type = 1, courseDetail = it, courseUploads = null) },
                recentActivityFragment)
        val titleList = listOf("Home", "Students", "Article", "Uploads", "Activity")

        viewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        }
    }

    private fun setData() {
        courseDetailViewModel.coursedetail.observe(this, Observer {
            toolbar.title = it.title
            courseHomeFragment = CourseHomeFragment.newInstance(it)
            setTabs()
        })
    }

    private fun initializeProgressBar() {
        courseDetailViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        courseDetailViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            showToast(message)
        })
    }
}
