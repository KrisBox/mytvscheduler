package io.github.krisbox.mytvscheduler.fragments.Other

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBDelete

/**
 * Description: TODO
 * @author Kris
 * Time: 12:46
 * Date: 15/06/2017
 * Last Updated: 15/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class SettingsFragment(internal var context: Context) : Fragment() {

    /**
     * Inflate the xml when creating the view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    /**
     * Add listeners for the buttons in the fragment
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val clearWL = view?.findViewById(R.id.clear_wl_button) as Button

        clearWL.setOnClickListener{
            val delete = TVSchedulerDBDelete(context)
            delete.deleteAllWatchlist()
            delete.db.close()
        }

        val clearCC = view.findViewById(R.id.clear_cc_button) as Button

        clearCC.setOnClickListener{
            val delete = TVSchedulerDBDelete(context)
            delete.deleteAllCache()
            delete.db.close()
        }
    }


}