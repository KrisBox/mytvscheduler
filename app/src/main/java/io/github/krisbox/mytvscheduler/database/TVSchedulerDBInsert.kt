package io.github.krisbox.mytvscheduler.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.dataclasses.Program
import io.github.krisbox.mytvscheduler.support.TransferCacheToPerm
import java.util.ArrayList

/**
 * Description: All inserting to the database is done here (TODO Optimisations)
 * @author Kris
 * Time: 14:54
 * Date: 11/06/2017
 * Last Updated: 11/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBInsert(internal var context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db: SQLiteDatabase = dbHelper.writableDatabase

    /**
     * Inserts all episodes of a program into the Watchlist Table
     */
    fun insertToWatchlist(program : Program, episodes: ArrayList<ArrayList<Episode>>){

        // Now only add to cahce when we add to the watchlist
        insertProgramCache(program)
        insertEpisodeCache(episodes)

        db.beginTransaction()
        try {
            for (i in 0..(episodes.size - 1)) {
                for (n in 0..(episodes[i].size - 1)) {
                    val values: ContentValues = ContentValues()
                    values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_PROGRAM_ID, episodes[i][n].programID)
                    values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_SEASON, episodes[i][n].seasonNo)
                    values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_EPISODE, episodes[i][n].episodeNo)
                    values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_WATCHED, "No")

                    val query = "SELECT * FROM " + TVSchedulerContract().DBEntry().TABLE_NAME_WATCHLIST + " WHERE (program_id = '" + episodes[i][n].programID + "' AND season_number = '" + episodes[i][n].seasonNo + "' AND episode_number = '" + episodes[i][n].episodeNo + "');"
                    val cursor = db.rawQuery(query, null)
                    if (cursor.count == 0) {
                        val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_WATCHLIST, null, values)
                    } else {
                    }
                    cursor.close()
                }
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    /**
     * Inserts into the Program Cache table
     */
    fun insertProgramCache(program: Program){
        val query = "SELECT * FROM "+ TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAM_CACHE +" WHERE program_id = '" + program.id + "';"
        val cursor = db.rawQuery(query, null)
        if (cursor.count == 0) {

            // Transfer the images from the image cahce to the permanent storage
            val transferCoverFile = TransferCacheToPerm(program.programPoster!!, program.id!!, "cover", context)
            val transferBackdropFile = TransferCacheToPerm(program.programBackdrop!!, program.id!!, "back", context)

            program.programPoster = transferCoverFile.newPathFull
            program.programBackdrop = transferBackdropFile.newPathFull

            val values: ContentValues = ContentValues()
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_PROGRAM_ID, program.id)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_NAME, program.programName)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_AIR, program.programRelease)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_RATING, program.programRating)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_COVER_IMAGE, program.programPoster)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_COVER_URL, program.programPosterURL)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_OVERVIEW, program.programOverview)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_BACK_IMAGE, program.programBackdrop)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_BACK_URL, program.programBackdropURL)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_GENRES, program.programGenres)

            val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAM_CACHE, null, values)
        }
        cursor.close()

    }

    /**
     * Inserts into the Episode Cache Table
     */
    fun insertEpisodeCache(episodes: ArrayList<ArrayList<Episode>>){
        db.beginTransaction()
        try {
            for (i in 0..(episodes.size - 1)) {
                for (n in 0..(episodes[i].size - 1)) {
                    val query = "SELECT * FROM " + TVSchedulerContract().DBEntry().TABLE_NAME_EPISODE_CACHE + " WHERE (program_id = '" + episodes[i][n].programID + "' AND season_number = '" + episodes[i][n].seasonNo + "' AND episode_number = '" + episodes[i][n].episodeNo + "');"
                    val cursor = db.rawQuery(query, null)
                    if (cursor.count == 0) {

                        val values: ContentValues = ContentValues()
                        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_PROGRAM_ID, episodes[i][n].programID)
                        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_SEASON_NUMBER, episodes[i][n].seasonNo)
                        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_NUMBER, episodes[i][n].episodeNo)
                        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_RATING, episodes[i][n].episodeRating)
                        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_NAME, episodes[i][n].episodeName)
                        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_AIR, episodes[i][n].episodeRelease)

                        val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_EPISODE_CACHE, null, values)
                    }
                    cursor.close()
                }
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }



}
