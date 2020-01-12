package org.wikiedufoundation.wikiedudashboard.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * Adapter class for ViewPager
 * ***/
class ViewPagerAdapter(
    manager: FragmentManager,
    private val fragmentList: List<Fragment>,
    private val fragmentTitleList: List<String>
) : FragmentPagerAdapter(manager) {

    /**
     * Use [getItem] get item per position
     ***/
    override fun getItem(position: Int): Fragment = fragmentList[position]

    /**
     * Use [getCount] get size of fragmentList
     ***/
    override fun getCount(): Int = fragmentList.size

    /**
     * Use [getPageTitle] get title per position
     ***/
    override fun getPageTitle(position: Int): CharSequence? = fragmentTitleList[position]

    /**
     * Use [getItemPosition] get item per position
     ***/
    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
}
