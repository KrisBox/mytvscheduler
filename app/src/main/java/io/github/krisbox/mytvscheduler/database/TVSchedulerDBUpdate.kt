package io.github.krisbox.mytvscheduler.database

import android.content.ContentValues
import android.content.Context

/**
 * Description: TODO
 * @author Kris
 * Time: 20:26
 * Date: 12/06/2017
 * Last Updated: 12/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBUpdate (internal var context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db = dbHelper.writableDatabase

    fun updateChecked (value: String, id: String, season: String, episode: String){
        val query = "UPDATE watch_list SET watched = '" + value + "' WHERE (programID = '"+ id +"' AND season_number = '"+ season +"' AND episode_number = '" + episode + "');"
        db.execSQL(query)
    }

}
