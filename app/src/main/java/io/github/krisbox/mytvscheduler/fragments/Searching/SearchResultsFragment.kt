package io.github.krisbox.mytvscheduler.fragments.Searching

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.Search
import io.github.krisbox.mytvscheduler.SearchRVAdapter
import io.github.krisbox.mytvscheduler.fragments.Program.ProgramInformationFragment
import io.github.krisbox.mytvscheduler.support.RecyclerItemClickListener



/**
 * Description: TO-DO
 *
 * @author Kris Box
 * Time: 21:47
 * Date: 30/05/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class SearchResultsFragment(private var query: String, internal var context: Context) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.search_results, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val programmes = Search(activity, query, "").searchData
        val rv = activity.findViewById(R.id.search_rv) as RecyclerView
        val llm = LinearLayoutManager(activity)
        rv.layoutManager = llm

        val adapter = SearchRVAdapter(programmes, context)
        rv.adapter = adapter

        rv.addOnItemTouchListener(RecyclerItemClickListener(context, rv, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val activity = context as Activity
                val fragmentManager = activity.fragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                val v = rv.layoutManager.findViewByPosition(position)
                val id = v.findViewById(R.id.program_id) as TextView

                val programInfo = ProgramInformationFragment(id.text.toString(), context)
                val searchFrag = fragmentManager.findFragmentByTag("SEARCH_FRAGMENT")
                if (searchFrag != null && searchFrag.isVisible){
                    fragmentTransaction.replace(R.id.search_fragment_placeholder, programInfo)
                    fragmentTransaction.commit()
                }
            }

            override fun onItemLongClick(view: View, position: Int) {
                //handle longClick if any
            }
        }))

    }
}