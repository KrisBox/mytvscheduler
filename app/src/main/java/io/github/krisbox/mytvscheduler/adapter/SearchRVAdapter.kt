package io.github.krisbox.mytvscheduler.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.krisbox.mytvscheduler.dataclasses.Program
import io.github.krisbox.mytvscheduler.R
import java.util.ArrayList

/**
 * Description: Adapter for the Recycler View in the Search
 * @author Kris Box
 * Time: 22:19
 * Date: 20/02/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class SearchRVAdapter internal constructor(internal var programmes: ArrayList<Program>, internal var context: Context) : RecyclerView.Adapter<SearchRVAdapter.TVViewHolder>() {
    /**
     * Inner class gets all the elements for data to be propagated
     */
    inner class TVViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var cv: CardView = itemView.findViewById(R.id.cv) as CardView
        internal var programName: TextView = itemView.findViewById(R.id.person_name) as TextView
        internal var programRelease: TextView = itemView.findViewById(R.id.person_age) as TextView
        internal var programRating: TextView = itemView.findViewById(R.id.imdbRating) as TextView
        internal var programPoster: ImageView = itemView.findViewById(R.id.imageView) as ImageView
        internal var programID: TextView = itemView.findViewById(R.id.program_id) as TextView

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
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_programme_view, viewGroup, false)
        val tvh = TVViewHolder(v)
        return tvh
    }

    /**
     * With the inner class, associate all the data to the elements to be shown
     */
    override fun onBindViewHolder(tvViewHolder: TVViewHolder, i: Int) {
        tvViewHolder.programName.text = programmes[i].programName
        tvViewHolder.programRelease.text = programmes[i].programRelease
        programmes[i].setImageToView(tvViewHolder.programPoster, "cover")
        tvViewHolder.programRating.text = programmes[i].programRating
        tvViewHolder.programID.text = programmes[i].id
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }


}
