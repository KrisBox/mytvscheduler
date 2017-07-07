package io.github.krisbox.mytvscheduler.fragments.Discover

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.krisbox.mytvscheduler.R

@SuppressLint("ValidFragment")
/**
 * Description: Discover Fragment (Featured, Most Popular, Similar To)
 * @author Kris
 * Time: 14:37
 * Date: 05/07/2017
 * Last Updated: 05/07/2017
 * Copyright (c) Kristofer Box 2017
 */
class DiscoverFragment(internal var context: Context) : Fragment() {
    /**
     * Inflate the xml when creating the view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    /**
     * Add listeners for the buttons in the fragment
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Do all the searching etc in here
    }
}