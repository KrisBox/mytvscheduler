package io.github.krisbox.mytvscheduler.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBHelper
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBUpdate
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import java.util.ArrayList

/**
 * Description: Recycler View Adapter for the cardview_episode lists in Program View
 * @author Kris
 * Time: 21:37
 * Date: 03/06/2017
 * Last Updated: 03/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class EpisodeRVAdapter internal constructor(internal var episodes: ArrayList<Episode>, internal var context: Context) : RecyclerView.Adapter<EpisodeRVAdapter.EPViewHolder>() {
    /**
     * Inner class gathers all the elements in which data will be propagated
     */
    inner class EPViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cv: CardView = itemView.findViewById(R.id.episode_cv) as CardView
        internal var episodeName: TextView = itemView.findViewById(R.id.episode_name) as TextView
        internal var episodeRelease: TextView = itemView.findViewById(R.id.air_date) as TextView
        internal var episodeRating: TextView = itemView.findViewById(R.id.imdbRating) as TextView
        internal var check: CheckBox = itemView.findViewById(R.id.episodeCheck) as CheckBox

    }

    /**
     * Get the size of the cardview_episode list
     */
    override fun getItemCount(): Int {
        return episodes.size
    }

    /**
     * When creating the view, create an instance of the inner class
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EpisodeRVAdapter.EPViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_episode, viewGroup, false)
        val tvh = EPViewHolder(v)
        return tvh
    }

    /**
     * Apply all the data to the elements of the cardview for each cardview_episode
     */
    override fun onBindViewHolder(tvViewHolder: EpisodeRVAdapter.EPViewHolder, i: Int) {
        tvViewHolder.episodeName.text = (episodes[i].episodeNo + ". " + episodes[i].episodeName)
        tvViewHolder.episodeRelease.text = episodes[i].episodeRelease
        tvViewHolder.episodeRating.text = episodes[i].episodeRating

        // Add check change to the database
        tvViewHolder.check.setOnCheckedChangeListener {buttonView, _ ->
            if (buttonView.isChecked){
                val update = TVSchedulerDBUpdate(context)
                update.updateChecked("Yes", episodes[i].programID, episodes[i].seasonNo, episodes[i].episodeNo)
                update.db.close()
            } else {
                val update = TVSchedulerDBUpdate(context)
                update.updateChecked("No", episodes[i].programID, episodes[i].seasonNo, episodes[i].episodeNo)
                update.db.close()
            }
        }

        // Load check box as ticked/not ticked if db says so
        val dbHelper = TVSchedulerDBHelper(context)
        val db = dbHelper.writableDatabase
        val query = "SELECT * FROM watch_list WHERE (programID = '" + episodes[i].programID + "' AND season_number = '"+ episodes[i].seasonNo +"' AND episode_number = '"+ episodes[i].episodeNo + "');"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            if (cursor.getString(7) == "Yes"){
                tvViewHolder.check.setChecked(true)
            } else {
                tvViewHolder.check.setChecked(false)
            }
        }
        cursor.close()
        db.close()

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }

}
