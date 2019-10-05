package org.wikiedufoundation.wikiedudashboard.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import me.relex.circleindicator.CircleIndicator
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import java.util.*

class WelcomeActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        viewPager = findViewById(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = viewPagerAdapter




        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("")
        fragmentList.add(WikiEducationDashboardFragment())
        titleList.add("")
        fragmentList.add(OutreachProgramsDashboardFragment())
        viewPagerAdapter!!.setTabData(fragmentList, titleList)
        viewPagerAdapter!!.notifyDataSetChanged()
    }
}
