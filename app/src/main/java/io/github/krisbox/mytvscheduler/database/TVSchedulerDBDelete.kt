package io.github.krisbox.mytvscheduler.database

import android.content.Context
import java.io.File

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

        //Deletes pictures from perm storage
        var permDir = context.filesDir.toString()

        var pathC = permDir + programID + "cover" + ".jpg"
        var pathP = permDir + programID + "back" + ".jpg"
        var fileC = File(pathC)
        var fileP = File(pathP)
        if (fileC.exists() && fileP.exists()){
            fileC.delete()
            fileP.delete()
        }

    }

    /**
     * Delete all the programs from the database
     */
    fun deleteAllWatchlist(){
        db.delete(TVSchedulerContract().DBEntry().TABLE_NAME_WATCHLIST, null, null)

        // Delete all images from the files directory
        trimCache(context.filesDir)

    }

    /**
     * Delete the cache
     */
    fun deleteAllCache(){
        trimCache(context.cacheDir)
    }

    /**
     * Checks if directory exists
     */
    fun trimCache(dir: File?) {
        try {
            if (dir != null && dir.isDirectory) {
                deleteDir(dir)
            }
        } catch (e: Exception) {}

    }

    /**
     * Deletes directory
     */
    fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }

        // The directory is now empty so delete it
        return dir!!.delete()
    }


}