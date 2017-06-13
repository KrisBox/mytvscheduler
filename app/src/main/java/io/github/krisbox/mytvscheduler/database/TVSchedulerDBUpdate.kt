package io.github.krisbox.mytvscheduler.database

import android.content.Context
import android.widget.Toast

/**
 * Description: All database updating is done in this file
 * @author Kris
 * Time: 20:26
 * Date: 12/06/2017
 * Last Updated: 12/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBUpdate (internal var context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db = dbHelper.writableDatabase

    /**
     * Nice and simple, changes the checked state of a specific cardview episode of a program
     */
    fun updateChecked (value: String, id: String, season: String, episode: String){

        val watchlist_query = ("UPDATE watch_list SET watched = '" + value + "' WHERE (programID = '"+ id +"' AND season_number = '"+ season +"' AND episode_number = '" + episode + "');")
        // No if count is 0 TODO
        val program_query = ("UPDATE programme SET watchlist = 'Yes' WHERE programID = '"+ id + "'")

        db.execSQL(program_query)
        db.execSQL(watchlist_query)
    }

}
