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
     * Nice and simple, changes the checked state of a specific card_view episode of a program
     */
    fun updateChecked (value: String, id: String, season: String, episode: String){

        val watchlist_query = ("UPDATE watch_list SET episode_watched = '" + value + "' WHERE (program_id = '"+ id +"' AND season_number = '"+ season +"' AND episode_number = '" + episode + "');")
        db.execSQL(watchlist_query)

        /*val count_query = ("SELECT * FROM watch_list WHERE (program_id = '" + id + "' AND watched = 'Yes');")
        val cursor = db.rawQuery(count_query, null)
        if (cursor.count == 0) {
            val program_query = ("UPDATE progr SET watchlist = 'No' WHERE program_id = '"+ id + "'")
            db.execSQL(program_query)
        } else {
            val program_query = ("UPDATE programme SET watchlist = 'Yes' WHERE program_id = '"+ id + "'")
            db.execSQL(program_query)
        }
        cursor.close()*/
    }

    /**
     * Set all episodes as watched for a specific season
     */
    fun updateSeason(id: String, seasonNo: String){
        val watchlist_query = ("UPDATE watch_list SET episode_watched = 'Yes' WHERE (program_id = '"+ id +"' AND season_number = '"+ seasonNo +"');")
        db.execSQL(watchlist_query)
    }

}
