package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_course_detail.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.CourseArticlesEditedFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.RetrofitCourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.home.CourseHomeFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view.StudentListFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter

/**
 * Activity view for course detail
 * ***/
class CourseDetailActivity : AppCompatActivity(), CourseDetailView {

    private val retrofitCourseDetailProvider: RetrofitCourseDetailProvider by inject()
    private val courseDetailPresenter: CourseDetailPresenter by inject {
        parametersOf(this, retrofitCourseDetailProvider)
    }

    private var enrolled = false

    private lateinit var url: String
    private lateinit var courseHomeFragment: CourseHomeFragment

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
//                val enrollParam = url.lastIndexOf("?enroll").plus(7).let { url.substring(it) }
                url = url.lastIndexOf("?enroll").let { url.substring(0, it) }
            }
        }

        tabLayout.setupWithViewPager(viewPager)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        url.let { courseDetailPresenter.requestCourseDetail(it) }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setData(data: CourseDetail) {
        toolbar.title = data.title
        courseHomeFragment = CourseHomeFragment.newInstance(data)
        setTabs()
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

    override fun showProgressBar(show: Boolean) {
        progressBar?.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
