package io.github.krisbox.mytvscheduler.fragments.Program

import android.support.v4.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.dataclasses.Program
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.searching.Search
import io.github.krisbox.mytvscheduler.adapter.EpisodePagerAdapter

/**
 * Description: Shows the information of a program given an ID
 * @author Kris
 * Time: 14:41
 * Date: 02/06/2017
 * Last Updated: 02/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class ProgramInformationFragment(internal var id: String, internal var context: Context) : Fragment() {
    /**
     * Inflate the xml when the view is created
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.program_information, container, false)
    }

    /**
     * Give all the data for the program view when the view is created:
     * Images, text, tabs, episode list etc
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val program = Search(context, "", id).programData
        val episodeList = Search(context, program.programNoOfSeasons.toString(), id).episodeData

        val title = view?.findViewById(R.id.title) as TextView
        title.text = program.programName

        val backdrop = view.findViewById(R.id.backdrop) as ImageView
        backdrop.setImageBitmap(program.programBackdrop)

        val date = view.findViewById(R.id.date) as TextView
        date.text = program.programRelease

        val rating = view.findViewById(R.id.imdbRating) as TextView
        rating.text = program.programRating

        val overview = view.findViewById(R.id.overview) as TextView
        overview.text = program.programOverview

        val viewPager = view.findViewById(R.id.pager) as ViewPager
        val tab = view.findViewById(R.id.tabs) as TabLayout
        setupViewPager(viewPager, program, episodeList)

        tab.setupWithViewPager(viewPager)


    }

    /**
     * Creates the view pager which allows the user to slide and scroll through the episodes
     */
    fun setupViewPager (viewPager: ViewPager, program: Program, episodeList: ArrayList<ArrayList<Episode>>){
        val adapter = EpisodePagerAdapter(childFragmentManager, episodeList, context)

        for ( i in 0..(program.programNoOfSeasons!!.toInt()) - 1){
            adapter.addFragTitle((i+1).toString())
        }

        viewPager.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}