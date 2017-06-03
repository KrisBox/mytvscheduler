package io.github.krisbox.mytvscheduler.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


/**
 * Description: TO-DO
 *
 * @author Kris Box
 * Time: 18:36
 * Date: 03/06/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class EpisodePagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }
}
