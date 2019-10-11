package org.wikiedufoundation.wikiedudashboard.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import me.relex.circleindicator.CircleIndicator
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import java.util.ArrayList

/**
* This class launch the welcome page
* when the users is logged out or on first login
*/
class WelcomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        viewPager = findViewById(R.id.viewPager)
        val indicator = findViewById<CircleIndicator>(R.id.indicator)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager?.adapter = viewPagerAdapter
        indicator.setViewPager(viewPager)
        indicator.createIndicators(2,0);
        indicator.animatePageSelected(2)

        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("")
        fragmentList.add(WikiEducationDashboardFragment())
        titleList.add("")
        fragmentList.add(OutreachProgramsDashboardFragment())
        viewPagerAdapter?.setTabData(fragmentList, titleList)
        viewPagerAdapter?.notifyDataSetChanged()
    }
}
