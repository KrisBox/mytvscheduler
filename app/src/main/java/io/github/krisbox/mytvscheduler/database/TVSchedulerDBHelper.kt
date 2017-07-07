package io.github.krisbox.mytvscheduler.database

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import android.content.Context

/**
 * Description: Helper for the database, this file creates the database where it's parent does the most work
 * @author Kris
 * Time: 14:45
 * Date: 11/06/2017
 * Last Updated: 11/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerDBHelper(context: Context) : SQLiteOpenHelper(context, TVSchedulerDBHelper.DATABASE_NAME, null, TVSchedulerDBHelper.DATABASE_VERSION) {

    /**
     * Create database and tables
     */
    override fun onCreate(db: SQLiteDatabase) {
        val tv = TVSchedulerContract()
        db.execSQL(tv.CREATE_PROGRAM_CACHE_TABLE)
        db.execSQL(tv.CREATE_EPISODE_CACHE_TABLE)
        db.execSQL(tv.CREATE_WATCHLIST_TABLE)
    }

    /**
     * Resets the database when updating.
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val tv = TVSchedulerContract()
        db.execSQL("DROP TABLE IF EXISTS " + tv.DBEntry().TABLE_NAME_PROGRAM_CACHE)
        db.execSQL("DROP TABLE IF EXISTS " + tv.DBEntry().TABLE_NAME_EPISODE_CACHE)
        db.execSQL("DROP TABLE IF EXISTS " + tv.DBEntry().TABLE_NAME_WATCHLIST)
        onCreate(db)
    }

    /**
     * Required for Super
     */
    companion object {
        // Increment when changing schema.
        val DATABASE_VERSION = 3
        val DATABASE_NAME = "TVScheduler.db"
    }
}