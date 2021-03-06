package io.github.krisbox.mytvscheduler.fragments.Program

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.adapter.WatchListRVAdapter
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBGet
import io.github.krisbox.mytvscheduler.support.RecyclerItemClickListener

@SuppressLint("ValidFragment")
/**
 * Description: Fragment for My Watchlist: Handles the loading of info from database 0
 * @author Kris
 * Time: 17:28
 * Date: 13/06/2017
 * Last Updated: 13/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class WatchlistFragment(internal var context: Context) : Fragment(){

    /**
     * Inflate the xml when creating the view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    /**
     * Create the recycler view for the results.
     * Add a listener for each card_view and create the card_view episode fragment when clicked
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val programs = TVSchedulerDBGet(context).getWatchlistPrograms()
        val rv = activity.findViewById(R.id.watchlist_rv) as RecyclerView
        val llm = LinearLayoutManager(activity)
        rv.layoutManager = llm

        val adapter = WatchListRVAdapter(programs, context)
        rv.adapter = adapter

        rv.addOnItemTouchListener(RecyclerItemClickListener(context, rv, object : RecyclerItemClickListener.OnItemClickListener {

            /**
             * Load the program view when a cardview is clicked
             */
            override fun onItemClick(view: View, position: Int) {
                val activity = context as FragmentActivity
                val fragmentManager = activity.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val v = rv.layoutManager.findViewByPosition(position)
                val id = v.findViewById(R.id.program_id) as TextView

                val programInfo = ProgramInformationFragment(id.text.toString(), context)
                val searchFrag = fragmentManager.findFragmentByTag("WATCHLIST_FRAGMENT")
                if (searchFrag != null && searchFrag.isVisible){
                    fragmentTransaction.replace(R.id.search_fragment_placeholder, programInfo)
                    fragmentTransaction.addToBackStack("PROGRAM_INFORMATION_FRAGMENT")
                    fragmentTransaction.commit()
                }
            }

            /**
             * May have use in future.
             */
            override fun onItemLongClick(view: View, position: Int) {
            }
        }))

    }
}