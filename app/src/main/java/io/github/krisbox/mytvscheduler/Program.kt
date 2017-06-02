package io.github.krisbox.mytvscheduler

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.github.krisbox.mytvscheduler.jsonparsing.GetImage

/**
 * Description: Stores the info for a specific program when searching. Also gets the poster from the webs.
 * @author Kris Box
 * Time: 22:24
 * Date: 20/02/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class Program(basicInfo: Array<String>, context: Context) {

    var programName: String = basicInfo[0]
    var programRelease: String = basicInfo[1]
    var programPoster: Bitmap?
    var programRating: String = basicInfo[3]

    init {

        if (basicInfo[2] != "null"){
            val image = GetImage("https://image.tmdb.org/t/p/w500/" + basicInfo[2])
            programPoster = image.image
        }else {
            programPoster = BitmapFactory.decodeResource(context.resources, R.drawable.no_poster)
        }
    }
}
