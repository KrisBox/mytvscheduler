package io.github.krisbox.mytvscheduler.database

import android.content.ContentValues
import android.content.Context
import io.github.krisbox.mytvscheduler.dataclasses.Program

/**
 * Description: Inserts a program (caching) into the database if not already presebt
 * @author Kris
 * Time: 14:54
 * Date: 11/06/2017
 * Last Updated: 11/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBInsertProgram(internal val programmes: ArrayList<Program>, context: Context) {
    val dbHelper = TVSchedulerDBHelper(context)
    val db = dbHelper.writableDatabase

    fun insertProgrammes(){
        for (i in 0..(programmes.size - 1)){
            val values: ContentValues = ContentValues()
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_PROGRAMID, programmes[i].id)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_NAME, programmes[i].programName)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_AIR, programmes[i].programRelease)
            values.put(TVSchedulerContract().DBEntry().COLUMN_NAME_RATING, programmes[i].programRating)
            // Cover image?? Need to research TODO
            // Only do if id is not already present TODO


            val newRowID: Long = db.insert(TVSchedulerContract().DBEntry().TABLE_NAME_PROGRAMME, null, values)
        }
    }


}
