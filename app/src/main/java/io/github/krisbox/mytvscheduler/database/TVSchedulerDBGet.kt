package io.github.krisbox.mytvscheduler.database

import android.content.Context
import io.github.krisbox.mytvscheduler.dataclasses.Program

/**
 * Description: All GET requests from the database are handled here
 * @author Kris
 * Time: 17:36
 * Date: 13/06/2017
 * Last Updated: 13/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBGet (internal var context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db = dbHelper.writableDatabase

    /**
     * Gets all the programs that have at least 1 episode marked as watched for the Watchlist view
     */
    fun getWatchlistPrograms(): ArrayList<Program> {
        val programs = ArrayList<Program>()

        // Get programIDs of all programs and add to array.
        val getIDs = "SELECT program_id FROM watch_list"
        val idCursor = db.rawQuery(getIDs, null)
        val idArray = ArrayList<String>()
        if (idCursor.moveToFirst()) {
            do {
                val programID = idCursor.getString(0)
                if ( !(idArray.contains(programID))) {
                    idArray.add(programID)
                }

            } while (idCursor.moveToNext())
        }
        idCursor.close()

        // Populate the data with given ids
        for ( i in 0..(idArray.size - 1)) {
            val program = Program(context)

            val program_query = "SELECT * FROM program_cache WHERE program_id = '" + idArray[i] + "';"
            val program_cursor = db.rawQuery(program_query, null)

            if (program_cursor.moveToFirst()) {
                program.id = program_cursor.getString(1)
                program.programPoster = program.fromByteArray(program_cursor.getBlob(2))
                program.programName = program_cursor.getString(4)
                program.programRelease = program_cursor.getString(5)
                program_cursor.close()

                val episode_query = "SELECT * FROM watch_list WHERE program_id = '" + program.id + "'"
                val episode_cursor = db.rawQuery(episode_query, null)
                program.programEpisodeTotal = (episode_cursor.count).toString()
                episode_cursor.close()

                val episode_watched_query = "SELECT * FROM watch_list WHERE (program_id = '" + program.id + "' AND episode_watched = 'Yes');"
                val episode_watched_cursor = db.rawQuery(episode_watched_query, null)
                program.programEpisodeViews = (episode_watched_cursor.count).toString()
                episode_watched_cursor.close()
            }

            programs.add(program)
        }
        db.close()
        return programs
    }

    fun getCoverUrl( id: String ) : String {
        val query = "SELECT cover_url FROM program_cache WHERE program_id = '" + id + "'"
        val cursor = db.rawQuery(query, null)
        var res = "null"
        if (cursor.moveToFirst()){
            res = cursor.getString(0)
        }
        cursor.close()
        db.close()
        return res
    }

    fun getBackUrl ( id: String) : String {
        val query = "SELECT back_url FROM program_cache WHERE program_id = '" + id + "'"
        val cursor = db.rawQuery(query, null)
        var res = "null"
        if (cursor.moveToFirst()){
            res = cursor.getString(0)
        }
        cursor.close()
        db.close()
        return res
    }



}