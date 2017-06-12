package io.github.krisbox.mytvscheduler.database

import android.provider.BaseColumns

/**
 * Description: Table declarations for the internal database used by the system
 * @author Kris
 * Time: 14:34
 * Date: 11/06/2017
 * Last Updated: 11/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class TVSchedulerContract {

    /**
     * Inner class with all the database declarations
     */
    inner class DBEntry : BaseColumns {
            val ID = "id"

            //TABLE: Programme
            val TABLE_NAME_PROGRAMME = "programme"
            val COLUMN_NAME_PROGRAMID = "programID"
            val COLUMN_NAME_COVER_IMAGE = "cover_image"
            val COLUMN_NAME_NAME = "name"
            val COLUMN_NAME_AIR = "air"
            val COLUMN_NAME_RATING = "rating"

            //TABLE: Programme Extended
            val TABLE_NAME_PROGRAMME_EXTENDED = "programme_extended"
            val COLUMN_NAME_EXT_PROGRAMID = "programID"
            val COLUMN_NAME_EXT_SEASON = "season_number"
            val COLUMN_NAME_EXT_EPISODE = "episode_number"
            val COLUMN_NAME_EXT_RATING = "episode_rating"
            val COLUMN_NAME_EXT_OVERVIEW = "overview"
            val COLUMN_NAME_EXT_AIR = "air_date"
            val COLUMN_NAME_POSTER_IMAGE = "poster_image"

            //TABLE: Watchlist
            //val TABLE_NAME_WATCHLIST = "programme_extended"
            //val COLUMN_NAME_WL_IMDBID = "imdbID"
            //val COLUMN_NAME_WL_RANK = "rank"

    }

    //Statement for Create Table Program
    val CREATE_PROGRAMME_TABLE = ("CREATE TABLE " + DBEntry().TABLE_NAME_PROGRAMME + "(" + DBEntry().ID + " INTEGER PRIMARY KEY,"
    + DBEntry().COLUMN_NAME_PROGRAMID + " TEXT, " + DBEntry().COLUMN_NAME_COVER_IMAGE + " BLOB, "
    + DBEntry().COLUMN_NAME_NAME + " TEXT, " + DBEntry().COLUMN_NAME_AIR + " TEXT, "
    + DBEntry().COLUMN_NAME_RATING + " TEXT" +
    ")")

    //Statement for Create Table Program Extended
    val CREATE_PROGRAMME_EXT_TABLE = ("CREATE TABLE "
    + DBEntry().TABLE_NAME_PROGRAMME_EXTENDED + "(" + DBEntry().ID + " INTEGER PRIMARY KEY,"
    + DBEntry().COLUMN_NAME_EXT_PROGRAMID + " TEXT, " + DBEntry().COLUMN_NAME_POSTER_IMAGE + " BLOB, "
    + DBEntry().COLUMN_NAME_EXT_SEASON + " TEXT, " + DBEntry().COLUMN_NAME_EXT_EPISODE + " TEXT, "
    + DBEntry().COLUMN_NAME_EXT_RATING + " TEXT, " + DBEntry().COLUMN_NAME_EXT_AIR + " TEXT, "
    + DBEntry().COLUMN_NAME_EXT_OVERVIEW + " TEXT" +
    ")")

    //val CREATE_WATCHLIST_TABLE = ("CREATE TABLE "
    //+ DBEntry().TABLE_NAME_WATCHLIST + "(" + DBEntry().ID + " INTEGER PRIMARY KEY,"
    //+ DBEntry().COLUMN_NAME_WL_IMDBID + " TEXT, " + DBEntry().COLUMN_NAME_WL_RANK + " TEXT)")
}

