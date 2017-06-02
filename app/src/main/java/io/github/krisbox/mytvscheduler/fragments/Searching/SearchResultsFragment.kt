package io.github.krisbox.mytvscheduler.fragments.Searching

import android.app.Fragment
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.Search
import io.github.krisbox.mytvscheduler.SearchRVAdapter

/**
 * Description: TO-DO
 *
 * @author Kris Box
 * Time: 21:47
 * Date: 30/05/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class SearchResultsFragment(private var query: String) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.search_results, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val llmSpinner = activity.findViewById(R.id.linlaHeaderProgress)
        val spinner = activity.findViewById(R.id.pbHeaderProgress)

        val programmes = Search(activity, query).data
        val rv = activity.findViewById(R.id.search_rv) as RecyclerView
        val llm = LinearLayoutManager(activity)
        rv.layoutManager = llm

        val adapter = SearchRVAdapter(programmes)
        rv.adapter = adapter

    }
}