package org.wikiedufoundation.wikiedudashboard.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import me.relex.circleindicator.CircleIndicator
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter

/**
 * This class launch the welcome page
 * when the users is logged out or on first login
 */
class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val myViewPager = findViewById<ViewPager>(R.id.viewPager)
        val indicator = findViewById<CircleIndicator>(R.id.indicator)

        indicator.apply {
            setViewPager(myViewPager)
            createIndicators(2, 0)
            animatePageSelected(2)
        }

        val fragmentList = listOf(WikiEducationDashboardFragment(), OutreachProgramsDashboardFragment())
        val titleList = listOf("", "")

        myViewPager.apply {
            adapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        }
    }
}
