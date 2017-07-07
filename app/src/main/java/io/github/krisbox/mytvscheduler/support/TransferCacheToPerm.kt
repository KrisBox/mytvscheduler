package io.github.krisbox.mytvscheduler.support

import java.io.File
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileOutputStream

/**
 * Description: Simply transfers a file from the cache to permanent storage.
 * @author Kris
 * Time: 13:48
 * Date: 05/07/2017
 * Last Updated: 05/07/2017
 * Copyright (c) Kristofer Box 2017
 */

class TransferCacheToPerm(internal var path: String, internal var id: String, internal var type: String, internal var context: Context) {
    var newPathFull: String = ""
    init {
        val file = File(path)
        if (file.exists()) {
            var permFileDir = context.filesDir
            val newPath = id + type + ".jpg"

            if (!permFileDir.exists()){
                permFileDir.mkdirs()
            }

            permFileDir = File(permFileDir, newPath)
            newPathFull = permFileDir.toString()

            val out = FileOutputStream(permFileDir)

            val bitmap = BitmapFactory.decodeFile(path)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()

        }

    }


}