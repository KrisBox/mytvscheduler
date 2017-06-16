package io.github.krisbox.mytvscheduler.fragments.Program

import android.os.Bundle
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.adapter.EpisodeRVAdapter
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBUpdate
import io.github.krisbox.mytvscheduler.dataclasses.Episode



/**
 * Description: Propagates the Episode cardview for a season of the selected cardviewepisode
 *
 * @author Kris Box
 * Time: 19:04
 * Date: 03/06/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class EpisodeFragment(private var episodes: ArrayList<ArrayList<Episode>>, internal var seasonNo: String, internal var context: Context) : Fragment(){

    /**
     * Inflate the xml when creating the view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_episode_list, container, false)
    }

    /**
     * Give the recycler view information when the view is created
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view?.findViewById(R.id.episode_rv) as RecyclerView
        val llm = LinearLayoutManager(activity)

        rv.layoutManager = llm

        val adapter = EpisodeRVAdapter(episodes, seasonNo, context)
        rv.adapter = adapter

        val seasonButt = view.findViewById(R.id.season_watched) as Button

        seasonButt.setOnClickListener {
            // Get season info and update all episodes
            val id = episodes[seasonNo.toInt() - 1][0].programID
            val seasonNo = episodes[seasonNo.toInt() - 1][0].seasonNo
            val update = TVSchedulerDBUpdate(context)
            update.updateSeason(id, seasonNo)
            update.db.close()

            // Refresh to show ticks
            val ft = fragmentManager.beginTransaction()
            ft.detach(this).attach(this).commit()
        }

    }

}