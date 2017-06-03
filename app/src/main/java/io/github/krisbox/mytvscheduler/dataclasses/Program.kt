package io.github.krisbox.mytvscheduler.dataclasses

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.searching.GetImage

/**
 * Description: Stores the info for a specific program when searching. Also gets the poster from the webs.
 * @author Kris Box
 * Time: 22:24
 * Date: 20/02/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class Program(internal var context: Context) {

    var programName: String? = null
    var programRelease: String? = null
    var programPoster: Bitmap? = null
    var programRating: String? = null
    var id: String? = null

    var programOverview: String? = null
    var programBackdrop: Bitmap? = null
    var programNoOfSeasons: String? = null

    fun giveSearchData(info: Array<String>){
        programName = info[0]
        programRelease = info[1]
        programRating = info[3]
        id = info[4]

        if (info[2] != "null"){
            val image = GetImage("https://image.tmdb.org/t/p/w500/" + info[2])
            programPoster = image.image
        }else {
            programPoster = BitmapFactory.decodeResource(context.resources, R.drawable.no_poster)
        }
    }

    fun giveProgramData(info: Array<String>){
        programName = info[0]
        programRelease = info[1]
        programRating = info[3]
        id = info[4]

        if (info[2] != "null"){
            val image = GetImage("https://image.tmdb.org/t/p/w500/" + info[2])
            programPoster = image.image
        }else {
            programPoster = BitmapFactory.decodeResource(context.resources, R.drawable.no_poster)
        }

        if (info[6] != "null"){
            val image = GetImage("https://image.tmdb.org/t/p/w500/" + info[6])
            programBackdrop = image.image
        }else {
            programBackdrop = BitmapFactory.decodeResource(context.resources, R.drawable.no_poster)
        }

        programOverview = info[5]
        programNoOfSeasons = info[7]
    }
}