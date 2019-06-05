package org.wikiedufoundation.wikiedudashboard.welcome

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.helper.ViewPagerAdapter

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife

class WelcomeActivity : AppCompatActivity() {

    @BindView(R.id.viewPager)
    internal var viewPager: ViewPager? = null

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        ButterKnife.bind(this)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = viewPagerAdapter
        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("Home")
        fragmentList.add(Welcome4Fragment())
        viewPagerAdapter!!.setTabData(fragmentList, titleList)
        viewPagerAdapter!!.notifyDataSetChanged()
    }
}
