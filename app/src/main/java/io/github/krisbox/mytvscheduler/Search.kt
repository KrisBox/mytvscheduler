package io.github.krisbox.mytvscheduler

import android.content.Context
import java.util.ArrayList
import io.github.krisbox.mytvscheduler.jsonparsing.FormatJSON
import io.github.krisbox.mytvscheduler.jsonparsing.GetData

/**
 * Description: Gets the data, and returns it as an ArrayList
 * @author Kris
 * Time: 22:47
 * Date: 18/04/2017
 * Last Updated: 18/04/2017
 * Copyright (c) Kristofer Box 2017
 */

class Search(private val context: Context, private val query: String) {

    // Return arraylist
    val data: ArrayList<Program>
        get() {
            val data = GetData(query)
            data.makeConnection()
            var info = data.getData()
            if (info != null) {
                val format = FormatJSON(info, context)
                val programmes = format.basicFormat()
                return programmes
            }

            val programmes = ArrayList<Program>()
            return programmes
        }

}
