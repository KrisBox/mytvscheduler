package io.github.krisbox.mytvscheduler.searching

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

/**
 * Description: Gets an Images and stores in a BitMap
 * @author Kris Box
 * Time: 21:18
 * Date: 28/01/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class GetImage(private val url: String) {
    private var bm: Bitmap? = null

    val image: Bitmap?
        get() {
            // Runs on another thread for efficiency
            val thread = Thread(Runnable {
                try {
                    val urlLink = URL(url)
                    bm = BitmapFactory.decodeStream(urlLink.openConnection().getInputStream())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
            thread.start()
            try {
                thread.join()
            } catch (e: Exception) {
            }

            return bm
        }


}
