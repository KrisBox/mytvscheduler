package io.github.krisbox.mytvscheduler.adapter

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.fragments.Program.EpisodeFragment


/**
 * Description: Adapter for the List of List of Episodes to show in Program View (For the Pager)
 *
 * @author Kris Box
 * Time: 18:36
 * Date: 03/06/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class EpisodePagerAdapter(manager: FragmentManager, internal var episodeList: ArrayList<ArrayList<Episode>>, internal var context: Context) : FragmentStatePagerAdapter(manager) {

    private val mFragmentTitleList = ArrayList<String>()

    /**
     * When the series tab is pressed, the new episode fragment is shown
     */
    override fun getItem(position: Int): Fragment {
        for ( i in 0..count) {
            if (i == position) {
                return EpisodeFragment(episodeList, (i+1).toString(), context)
            }
        }
        // This will never happen but is needed
        return EpisodeFragment(episodeList, "1", context)
    }

    /**
     * Returns the number of series
     */
    override fun getCount(): Int {
        return episodeList.size
    }

    /**
     * Adds the title for the tab
     * Will be just 1 through n
     */
    fun addFragTitle(title: String) {
        mFragmentTitleList.add(title)
    }


    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }
}
