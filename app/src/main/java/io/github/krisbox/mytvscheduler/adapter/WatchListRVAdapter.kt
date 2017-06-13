package io.github.krisbox.mytvscheduler.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.dataclasses.Program
import java.util.ArrayList

/**
 * Description: Adapter for the card views for the watchlist section
 * @author Kris
 * Time: 17:11
 * Date: 13/06/2017
 * Last Updated: 13/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class WatchListRVAdapter internal constructor(internal var programmes: ArrayList<Program>, internal var context: Context) : RecyclerView.Adapter<WatchListRVAdapter.TVViewHolder>() {
    /**
     * Inner class gets all the elements for data to be propagated
     */
    inner class TVViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cv: CardView = itemView.findViewById(R.id.watchlist_cv) as CardView
        internal var programName: TextView = cv.findViewById(R.id.program_name) as TextView
        internal var programRelease: TextView = cv.findViewById(R.id.program_release) as TextView
        internal var programEpisodesWatched: TextView = cv.findViewById(R.id.episodes_watched) as TextView
        internal var programEpisodesTotal: TextView = cv.findViewById(R.id.episodes_total) as TextView
        internal var programPoster: ImageView = cv.findViewById(R.id.imageView) as ImageView
        internal var programID: TextView = cv.findViewById(R.id.program_id) as TextView

    }

    /**
     * Returns the number of results
     */
    override fun getItemCount(): Int {
        return programmes.size
    }

    /**
     * When creating the view, we return the inner class of data
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): TVViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_watchlist, viewGroup, false)
        val tvh = TVViewHolder(v)
        return tvh
    }

    /**
     * With the inner class, associate all the data to the elements to be shown
     */
    override fun onBindViewHolder(tvViewHolder: TVViewHolder, i: Int) {
        tvViewHolder.programName.text = programmes[i].programName
        tvViewHolder.programRelease.text = programmes[i].programRelease
        tvViewHolder.programPoster.setImageBitmap(programmes[i].programPoster)
        tvViewHolder.programEpisodesWatched.text = programmes[i].programEpisodeViews
        tvViewHolder.programEpisodesTotal.text = (" of " + programmes[i].programEpisodeTotal)
        tvViewHolder.programID.text = programmes[i].id
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}
