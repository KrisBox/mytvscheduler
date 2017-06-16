package io.github.krisbox.mytvscheduler.searching

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBGet
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

    fun getCoverArray(context: Context) : ByteArray {
            val query = "SELECT cover_image FROM program_cache WHERE program_id = '" + url + "';"
            val get = TVSchedulerDBGet(context)
            val cursor = get.db.rawQuery(query, null)
            cursor.moveToFirst()
            val arr = cursor.getBlob(0)
            cursor.close()
            get.db.close()
            return arr
    }

    fun getBackArray(context: Context) : ByteArray {
        val query = "SELECT back_image FROM program_cache WHERE program_id = '" + url + "';"
        val get = TVSchedulerDBGet(context)
        val cursor = get.db.rawQuery(query, null)
        cursor.moveToFirst()
        val arr = cursor.getBlob(0)
        cursor.close()
        get.db.close()
        return arr
    }
}
