package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_course_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.view.ArticleFragment
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.view.CourseFragment
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.viewmodel.CampaignHomeViewModel
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.view.StudentFragment
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

class CampaignDetailActivity : AppCompatActivity() {

    private var enrolled = false
    private lateinit var url: String
    private lateinit var campaignHomeFragment: CampaignHomeFragment
    private val campaignDetailsViewModel by viewModel<CampaignHomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_campaign_detail)

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

        url?.let { campaignDetailsViewModel.requestCampaignDetail(it) }

        setData()
        initializeProgressBar()
        initializeToaster()
        tabLayout.setupWithViewPager(viewPager)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    /**
     * Set course detail tabs
     * ***/
    private fun setTabs() {
        val courseFragment = CourseFragment()
        val bundle = Bundle()
        bundle.putString("url", url)
        courseFragment.arguments = bundle

        val articleFragment = ArticleFragment()
        val bundle2 = Bundle()
        bundle2.putString("url", url)
        articleFragment.arguments = bundle2

        val studentFragment = StudentFragment()
        val bundle3 = Bundle()
        bundle3.putString("url", url)
        studentFragment.arguments = bundle3

        val fragmentList = listOf(campaignHomeFragment,
                courseFragment,
                articleFragment,
                studentFragment)

        val titleList = listOf("Home", "Courses", "Articles", "Students")

        viewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        }
    }

    private fun setData() {
        campaignDetailsViewModel.campaignDetail.observe(this, Observer {
            Timber.d("hello  $it.title")
            toolbar.title = it.title
            campaignHomeFragment = CampaignHomeFragment.newInstance(it)
            setTabs()
        })
    }

    private fun initializeProgressBar() {
        campaignDetailsViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        campaignDetailsViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            showToast(message)
        })
    }
}
