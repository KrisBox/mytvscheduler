package io.github.krisbox.mytvscheduler.database

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.dataclasses.Program

/**
 * Description: Inserts a program (caching) into the database if not already present
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
     * Inserts into the Program table
     */
    fun insertProgrammes(programmes: ArrayList<Program>){
        for (i in 0..(programmes.size - 1)){
            val values: ContentValues = ContentValues()
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PROGRAMID, programmes[i].id)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_NAME, programmes[i].programName)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_AIR, programmes[i].programRelease)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_RATING, programmes[i].programRating)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_COVER_IMAGE, programmes[i].toByteArrayCover())

            val query = "SELECT * FROM programme WHERE programID = '" + programmes[i].id + "';"
            val cursor = db.rawQuery(query, null)
            if (cursor.count == 0) {
                val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAMME, null, values)
            } else {}
            cursor.close()
            }
    }

    /**
     * Inserts into the Program Extended Table
     */
    fun insertProgramInfo(program: Program){
        val values: ContentValues = ContentValues()
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EXT_PROGRAMID, program.id)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EXT_OVERVIEW, program.programOverview)
        values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_EXT_POSTER_IMAGE, program.toByteArrayPoster())

        val query = "SELECT * FROM programme_extended WHERE programID = '" + program.id + "';"
        val cursor = db.rawQuery(query, null)
        if (cursor.count == 0) {
            val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAMME_EXTENDED, null, values)
            Toast.makeText(context, "Program added to db: " + program.programName, Toast.LENGTH_SHORT).show()
        } else {}
        cursor.close()
    }

    /**
     * Inserts into the Watchlist Table
     */
    fun insertEpisodeInfo(episode: Episode){
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

    }

}
