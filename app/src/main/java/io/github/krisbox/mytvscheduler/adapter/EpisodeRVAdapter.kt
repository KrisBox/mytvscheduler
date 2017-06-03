package io.github.krisbox.mytvscheduler.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import java.util.ArrayList

/**
 * Description: TODO
 * @author Kris
 * Time: 21:37
 * Date: 03/06/2017
 * Last Updated: 03/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class EpisodeRVAdapter internal constructor(internal var episodes: ArrayList<Episode>, internal var context: Context) : RecyclerView.Adapter<EpisodeRVAdapter.EPViewHolder>() {
    inner class EPViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cv: CardView = itemView.findViewById(R.id.episode_cv) as CardView
        internal var episodeName: TextView = itemView.findViewById(R.id.episode_name) as TextView
        internal var episodemRelease: TextView = itemView.findViewById(R.id.air_date) as TextView
        internal var episodeRating: TextView = itemView.findViewById(R.id.imdbRating) as TextView

    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EpisodeRVAdapter.EPViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.episode, viewGroup, false)
        val tvh = EPViewHolder(v)
        return tvh
    }

    override fun onBindViewHolder(tvViewHolder: EpisodeRVAdapter.EPViewHolder, i: Int) {
        tvViewHolder.episodeName.text = episodes[i].episodeName
        tvViewHolder.episodemRelease.text = episodes[i].episodeRelease
        tvViewHolder.episodeRating.text = episodes[i].episodeRating
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }

}
