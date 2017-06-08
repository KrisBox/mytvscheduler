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
 * Description: TO-DO
 *
 * @author Kris Box
 * Time: 18:36
 * Date: 03/06/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class EpisodePagerAdapter(manager: FragmentManager, internal var episodeList: ArrayList<ArrayList<Episode>>, internal var context: Context) : FragmentStatePagerAdapter(manager) {

    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        for ( i in 0..count) {
            if (i == position) {
                return EpisodeFragment(episodeList[i], context)
            }
        }
        return EpisodeFragment(episodeList[0], context)
    }

    override fun getCount(): Int {
        return episodeList.size
    }

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
