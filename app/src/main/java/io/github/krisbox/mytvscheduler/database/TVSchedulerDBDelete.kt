package io.github.krisbox.mytvscheduler.database

import android.content.Context

/**
 * Description: Do all Delete operations here
 * @author Kris
 * Time: 16:00
 * Date: 16/06/2017
 * Last Updated: 16/06/2017
 * Copyright (c) Kristofer Box 2017
 */
class TVSchedulerDBDelete (internal var context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db = dbHelper.writableDatabase

    /**
     * Delete a program from the database
     */
    fun deleteFromWatchlist(programID: String) {
        db.delete(TVSchedulerContract().DBEntry().TABLE_NAME_WATCHLIST, TVSchedulerContract().DBEntry().COLUMN_NAME_WL_PROGRAM_ID + "=" + programID, null)
    }

    /**
     * Delete all the programs from the database
     */
    fun deleteAllWatchlist(){
        db.delete(TVSchedulerContract().DBEntry().TABLE_NAME_WATCHLIST, null, null)
    }

    /**
     * Delete the cache
     */
    fun deleteAllCache(){
        db.delete(TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAM_CACHE, null, null)
        db.delete(TVSchedulerContract().DBEntry().TABLE_NAME_EPISODE_CACHE, null, null)
    }
}