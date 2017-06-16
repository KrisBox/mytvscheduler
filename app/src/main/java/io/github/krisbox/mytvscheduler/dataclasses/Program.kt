package io.github.krisbox.mytvscheduler.dataclasses

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBGet
import io.github.krisbox.mytvscheduler.searching.GetImage
import java.io.ByteArrayOutputStream

/**
 * Description: Stores the info for a specific program when searching. Also gets the poster from the webs.
 * @author Kris Box
 * Time: 22:24
 * Date: 20/02/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class Program(internal var context: Context) {

    // Program information declarations
    var programName: String? = null
    var programRelease: String? = null
    var programPoster: Bitmap? = null
    var programPosterURL: String? = null
    var programRating: String? = null
    var id: String? = null

    // Program Extended information declarations
    var programOverview: String? = null
    var programBackdrop: Bitmap? = null
    var programBackdropURL: String? = null
    var programNoOfSeasons: String? = null

    //Watchlist
    var programEpisodeViews: String? = null
    var programEpisodeTotal: String? = null

    /**
     * Used in the SearchView when showing the RecyclerView
     */
    fun giveSearchData(info: Array<String>){
        programName = info[0]
        programRelease = info[1]
        programPosterURL = info[2]
        programRating = info[3]
        id = info[4]

        // Get image if available, show default if not
        if (programPosterURL != "null"){
            val dbPosterUrl = TVSchedulerDBGet(context).getCoverUrl(id!!)
            if (dbPosterUrl == programPosterURL){
                var image = GetImage(id!!).getCoverArray(context)
                programPoster = fromByteArray(image)
            } else {
                val image = GetImage("https://image.tmdb.org/t/p/original/" + programPosterURL)
                programPoster = image.image
            }
        }else {
            programPoster = BitmapFactory.decodeResource(context.resources, R.drawable.no_poster)
        }
    }

    /**
     * Used in the Program View, using the extended information
     */
    fun giveProgramData(info: Array<String>){
        programName = info[0]
        programRelease = info[1]
        programPosterURL = info[2]
        programBackdropURL = info[6]
        programRating = info[3]
        id = info[4]

        // Get images if available, use defaults if not
        if (programPosterURL != "null"){
            val dbPosterUrl = TVSchedulerDBGet(context).getCoverUrl(id!!)
            if (dbPosterUrl == programPosterURL){
                var image = GetImage(id!!).getCoverArray(context)
                programPoster = fromByteArray(image)
            } else {
                val image = GetImage("https://image.tmdb.org/t/p/w500/" + programPosterURL)
                programPoster = image.image
            }
        }else {
            programPoster = BitmapFactory.decodeResource(context.resources, R.drawable.no_poster)
        }

        if (programBackdropURL != "null"){
            val dbBackdropUrl = TVSchedulerDBGet(context).getBackUrl(id!!)
            if (dbBackdropUrl == programBackdropURL){
                var image = GetImage(id!!).getBackArray(context)
                programBackdrop = fromByteArray(image)
            } else {
                val image = GetImage("https://image.tmdb.org/t/p/w500/" + programBackdropURL)
                programBackdrop = image.image
            }
        }else {
            programBackdrop = BitmapFactory.decodeResource(context.resources, R.drawable.no_poster)
        }

        programOverview = info[5]
        programNoOfSeasons = info[7]
    }


    fun toByteArrayCover(): ByteArray {
        val stream = ByteArrayOutputStream()
        programPoster!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun toByteArrayPoster(): ByteArray {
        val stream = ByteArrayOutputStream()
        programBackdrop!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun fromByteArray(array: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(array, 0, array.size)
    }
}
