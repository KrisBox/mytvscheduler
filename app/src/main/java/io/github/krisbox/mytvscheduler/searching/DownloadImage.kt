package io.github.krisbox.mytvscheduler.searching

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.io.File
import java.io.FileOutputStream
import java.net.URL

/**
 * Description: Uses Async to download an image on a different thread, and store it in the cache
 * @author Kris
 * Time: 14:22
 * Date: 06/07/2017
 * Last Updated: 06/07/2017
 * Copyright (c) Kristofer Box 2017
 */
class DownloadImage(internal var context: Context, internal var id: String, internal var type: String, internal var imageView: ImageView) : AsyncTask<String, String, String>() {
    var finalPath: String = ""
    val progressDialog = ProgressDialog(context)

    /**
     * Before starting the download
     */
    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.setMessage("Downloading images...");
        progressDialog.isIndeterminate = true;
        progressDialog.show();
    }

    /**
     * Downloading
     */
    override fun doInBackground(vararg params: String?): String? {
        var myDir = context.cacheDir
        val thread = Thread(Runnable {
            try {
                val urlLink = URL(params[0])
                val inputStream = urlLink.openConnection().getInputStream()
                val location = id + type + ".jpg"

                if (!myDir.exists()){
                    myDir.mkdirs()
                }

                myDir = File(myDir, location)
                val out = FileOutputStream(myDir)

                val bitmap = BitmapFactory.decodeStream(inputStream)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.flush()
                out.close()
                println("File saved: " + myDir.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
        }
        finalPath = myDir.toString()
        return null
    }

    /**
     * After downloading
     */
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        progressDialog.dismiss()

        // Apply image view with the image itself.
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
        imageView.setImageBitmap(BitmapFactory.decodeFile(finalPath, options))
    }

}