package io.github.krisbox.mytvscheduler.fragments.Program

import android.os.Bundle
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.adapter.EpisodeRVAdapter
import io.github.krisbox.mytvscheduler.dataclasses.Episode

/**
 * Description: TO-DO
 *
 * @author Kris Box
 * Time: 19:04
 * Date: 03/06/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class EpisodeFragment(private var episodes: ArrayList<Episode>, internal var context: Context) : Fragment(){
    // need to have recycler view in here for each episode

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.episode_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = activity.findViewById(R.id.episode_rv) as RecyclerView
        val llm = LinearLayoutManager(activity)
        rv.layoutManager = llm

        val adapter = EpisodeRVAdapter(episodes, context)
        rv.adapter = adapter

    }

}