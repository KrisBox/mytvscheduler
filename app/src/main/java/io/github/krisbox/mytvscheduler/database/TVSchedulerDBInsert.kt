package io.github.krisbox.mytvscheduler.database

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.dataclasses.Program
import java.util.ArrayList

/**
 * Description: All inserting to the database is done here
 * @author Kris
 * Time: 14:54
 * Date: 11/06/2017
 * Last Updated: 11/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBInsert(internal var context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db = dbHelper.writableDatabase

    /**
     * Inserts into the Program Cache table
     */
    fun insertProgramCache(program: Program){
        val values: ContentValues = ContentValues()
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_PROGRAM_ID, program.id)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_NAME, program.programName)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_AIR, program.programRelease)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_RATING, program.programRating)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_COVER_IMAGE, program.toByteArrayCover())
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_COVER_URL, program.programPosterURL)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_OVERVIEW, program.programOverview)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_BACK_IMAGE, program.toByteArrayPoster())
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PC_BACK_URL, program.programBackdropURL)

        val query = "SELECT * FROM "+ TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAM_CACHE +" WHERE program_id = '" + program.id + "';"
        val cursor = db.rawQuery(query, null)
        if (cursor.count == 0) {
            val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAM_CACHE, null, values)
        } else {}
        cursor.close()

    }

    /**
     * Inserts into the Episode Cache Table
     */
    fun insertEpisodeCache(episodes: ArrayList<ArrayList<Episode>>){

        for ( i in 0..(episodes.size - 1)){
            for ( n in 0..(episodes[i].size - 1)){
                val values: ContentValues = ContentValues()
                values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_PROGRAM_ID, episodes[i][n].programID)
                values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_SEASON_NUMBER, episodes[i][n].seasonNo)
                values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_NUMBER, episodes[i][n].episodeNo)
                values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_RATING, episodes[i][n].episodeRating)
                values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_NAME, episodes[i][n].episodeName)
                values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EC_EPISODE_AIR, episodes[i][n].episodeRelease)

                val query = "SELECT * FROM "+TVSchedulerContract().DBEntry().TABLE_NAME_EPISODE_CACHE+" WHERE (program_id = '" + episodes[i][n].programID + "' AND season_number = '" + episodes[i][n].seasonNo + "' AND episode_number = '" + episodes[i][n].episodeNo + "');"
                val cursor = db.rawQuery(query, null)
                if (cursor.count == 0) {
                    val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_EPISODE_CACHE, null, values)
                } else {}
                cursor.close()

            }
        }
    }

    /**
     * Inserts all episodes of a program into the Watchlist Table
     */
    fun insertToWatchlist(episodes: ArrayList<ArrayList<Episode>>){
        for ( i in 0..(episodes.size - 1)) {
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
    }

    /*fun insertEpisodeInfo(episode: Episode){
        val values: ContentValues = ContentValues()
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_PROGRAMID, episode.programID)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_NAME, episode.episodeName)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_AIR, episode.episodeRelease)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_SEASON, episode.seasonNo)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_EPISODE, episode.episodeNo)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_RATING, episode.episodeRating)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_RANK, "0")
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_WL_WATCHED, "No")


        val query = "SELECT * FROM watch_list WHERE (programID = '" + episode.programID + "' AND season_number = '"+ episode.seasonNo +"' AND episode_number = '"+ episode.episodeNo + "');"
        val cursor = db.rawQuery(query, null)
        if (cursor.count == 0) {
            val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_WATCHLIST, null, values)
        } else {}
        cursor.close()

    }*/

}
