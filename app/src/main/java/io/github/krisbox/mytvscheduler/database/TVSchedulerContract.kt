package io.github.krisbox.mytvscheduler.database

import android.provider.BaseColumns

/**
 * Description: Table declarations for the internal database used by the system
 * @author Kris
 * Time: 14:05
 * Date: 16/06/2017
 * @version: 2.0 : Redesigned database
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerContract {

    /**
     * Inner class with all the database declarations
     */
    inner class DBEntry : BaseColumns {
            // Required by all Android Tables
            val ID = "id"

            //TABLE: Program Cache
            val TABLE_NAME_PROGRAM_CACHE = "program_cache"
            val COLUMN_NAME_PC_PROGRAM_ID = "program_id"
            val COLUMN_NAME_PC_COVER_IMAGE = "cover_image"
            val COLUMN_NAME_PC_BACK_IMAGE = "back_image"
            val COLUMN_NAME_PC_NAME = "name"
            val COLUMN_NAME_PC_AIR = "first_air_date"
            val COLUMN_NAME_PC_RATING = "average_rating"
            val COLUMN_NAME_PC_OVERVIEW = "overview"
            val COLUMN_NAME_PC_COVER_URL = "cover_url"
            val COLUMN_NAME_PC_BACK_URL = "back_url"

            //TABLE: Episode Cache
            val TABLE_NAME_EPISODE_CACHE = "episode_cache"
            val COLUMN_NAME_EC_PROGRAM_ID = "program_id"
            val COLUMN_NAME_EC_SEASON_NUMBER = "season_number"
            val COLUMN_NAME_EC_EPISODE_NUMBER = "episode_number"
            val COLUMN_NAME_EC_EPISODE_NAME = "episode_name"
            val COLUMN_NAME_EC_EPISODE_AIR = "first_air_date"
            val COLUMN_NAME_EC_EPISODE_RATING = "episode_rating"

            //TABLE: Watchlist
            val TABLE_NAME_WATCHLIST = "watch_list"
            val COLUMN_NAME_WL_PROGRAM_ID = "program_id"
            val COLUMN_NAME_WL_SEASON = "season_number"
            val COLUMN_NAME_WL_EPISODE = "episode_number"
            val COLUMN_NAME_WL_WATCHED = "episode_watched"

    }

    //Statement for Create Table Program Cache
    val CREATE_PROGRAM_CACHE_TABLE = ("CREATE TABLE " + DBEntry().TABLE_NAME_PROGRAM_CACHE + "(" + DBEntry().ID + " INTEGER PRIMARY KEY,"
    + DBEntry().COLUMN_NAME_PC_PROGRAM_ID + " TEXT, " + DBEntry().COLUMN_NAME_PC_COVER_IMAGE + " BLOB, "
    + DBEntry().COLUMN_NAME_PC_BACK_IMAGE + " BLOB, " + DBEntry().COLUMN_NAME_PC_NAME + " TEXT, "
    + DBEntry().COLUMN_NAME_PC_AIR + " TEXT, " + DBEntry().COLUMN_NAME_PC_RATING + " TEXT, "
    + DBEntry().COLUMN_NAME_PC_OVERVIEW + " TEXT, " + DBEntry().COLUMN_NAME_PC_COVER_URL + " TEXT, "
    + DBEntry().COLUMN_NAME_PC_BACK_URL + " TEXT" +
    ")")

    //Statement for Create Table Episode Cache
    val CREATE_EPISODE_CACHE_TABLE = ("CREATE TABLE "
    + DBEntry().TABLE_NAME_EPISODE_CACHE + "(" + DBEntry().ID + " INTEGER PRIMARY KEY,"
    + DBEntry().COLUMN_NAME_EC_PROGRAM_ID + " TEXT, " + DBEntry().COLUMN_NAME_EC_SEASON_NUMBER + " TEXT, "
    + DBEntry().COLUMN_NAME_EC_EPISODE_NUMBER + " TEXT, " + DBEntry().COLUMN_NAME_EC_EPISODE_NAME + " TEXT, "
    + DBEntry().COLUMN_NAME_EC_EPISODE_AIR + " TEXT, " + DBEntry().COLUMN_NAME_EC_EPISODE_RATING + " TEXT " +
    ")")

    //Statement for Create Table Watchlist
    val CREATE_WATCHLIST_TABLE = ("CREATE TABLE "
    + DBEntry().TABLE_NAME_WATCHLIST + "(" + DBEntry().ID + " INTEGER PRIMARY KEY,"
    + DBEntry().COLUMN_NAME_WL_PROGRAM_ID + " TEXT, " + DBEntry().COLUMN_NAME_WL_SEASON + " TEXT, "
    + DBEntry().COLUMN_NAME_WL_EPISODE + " TEXT, " + DBEntry().COLUMN_NAME_WL_WATCHED + " TEXT " +
    ")")
}

