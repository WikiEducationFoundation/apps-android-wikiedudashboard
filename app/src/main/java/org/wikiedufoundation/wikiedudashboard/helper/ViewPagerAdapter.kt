package org.wikiedufoundation.wikiedudashboard.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

import java.util.ArrayList

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private var fragmentList: List<Fragment?> =  ArrayList()
    private var fragmentTitleList: List<String> = ArrayList()

    override fun getItem(position: Int): Fragment? {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun setTabData(fragmentList: List<Fragment?>, fragmentTitleList: List<String>) {
        this.fragmentList = fragmentList
        this.fragmentTitleList = fragmentTitleList
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }


    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
