package io.github.krisbox.mytvscheduler.dataclasses

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.searching.DownloadImage
import java.io.File

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
    var programPoster: String? = null
    var programPosterURL: String? = null
    var programRating: String? = null
    var programGenres: String? = null
    var id: String? = null

    // Program Extended information declarations
    var programOverview: String? = null
    var programBackdrop: String? = null
    var programBackdropURL: String? = null
    var programNoOfSeasons: String? = null

    // Watchlist
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
        programGenres = info[5]
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

        programOverview = info[5]
        programNoOfSeasons = info[7]
    }

    /**
     * Used to apply the image to the image view once downloaded.
     * @param imageView : Imageview
     * @param type : String (Either cover or back)
     */
    fun setImageToView(imageView: ImageView, type: String){
        var url: String?
        var imagePath: String = ""
        var alreadySet: Boolean = false
        if (type == "cover"){
            url = programPosterURL
        } else {
            url = programBackdropURL!!
        }

        if (url != "null"){

            // Does it already exist in cache or permanent
            val potentialPathCache = context.cacheDir.toString() + "/" + id!! + type + ".jpg"
            val potentialPathPerm = context.filesDir.toString() + "/" + id!! + type + ".jpg"
            if (imageInCache(potentialPathCache)) {
                imagePath = potentialPathCache
            } else if (imageInCache(potentialPathPerm)){
                imagePath = potentialPathPerm
            // If not, download it
            } else {
                val download = DownloadImage(context, id!!, type, imageView)
                download.execute("https://image.tmdb.org/t/p/original/" + url)
                imagePath = download.finalPath
                alreadySet = true
            }

        }else {
            imagePath = "default"
        }

        // It will be set if it has been downloaded
        if (!alreadySet) {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.RGB_565

            if (imagePath != "default"){
                imageView.setImageBitmap(BitmapFactory.decodeFile(imagePath, options))
            } else {
                imageView.setImageBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.no_poster, options))
            }
        }

        if (type == "cover") {
            programPoster = imagePath
        } else {
            programBackdrop = imagePath
        }
    }

    /**
     * Checks if an image is in the cache
     */
    fun imageInCache( path: String) : Boolean {
        val file = File(path)
        return file.exists()
    }
}
