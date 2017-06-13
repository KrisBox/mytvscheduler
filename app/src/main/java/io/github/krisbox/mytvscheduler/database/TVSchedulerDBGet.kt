package io.github.krisbox.mytvscheduler.database

import android.content.Context
import io.github.krisbox.mytvscheduler.dataclasses.Program

/**
 * Description: TODO
 * @author Kris
 * Time: 17:36
 * Date: 13/06/2017
 * Last Updated: 13/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBGet (internal var context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db = dbHelper.writableDatabase


    fun getWatchlistPrograms(): ArrayList<Program> {
        val programs = ArrayList<Program>()

        val query = "SELECT * FROM programme WHERE watchlist = 'Yes'"
        //val program_cursor = db.query(TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAMME, null, TVSchedulerContract().DBEntry().COLUMN_NAME_WATCHLIST + "=?", arrayOf("Yes"), null, null, null, null)
        val program_cursor = db.rawQuery(query, null)
        if (program_cursor.moveToFirst()) {
            do {
                val program = Program(context)
                program.id = program_cursor.getString(1)
                program.programName = program_cursor.getString(3)
                program.programRelease = program_cursor.getString(4)
                program.programPoster = program.fromByteArrayCover(program_cursor.getBlob(2))
                val episode_query = "SELECT * FROM watch_list WHERE programID = '" + program.id + "'"
                val episode_cursor = db.rawQuery(episode_query, null)
                println(program.id)
                program.programEpisodeTotal = (episode_cursor.count).toString()
                episode_cursor.close()
                val episode_watched_query = "SELECT * FROM watch_list WHERE (programID = '" + program.id + "' AND watched = 'Yes');"
                val episode_watched_cursor = db.rawQuery(episode_watched_query, null)
                program.programEpisodeViews = (episode_watched_cursor.count).toString()
                episode_watched_cursor.close()

                programs.add(program)
            } while (program_cursor.moveToNext())

        }

        program_cursor.close()
        db.close()
        return programs
    }



}