package io.github.krisbox.mytvscheduler.jsonparsing


import android.content.Context
import io.github.krisbox.mytvscheduler.Program
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Description: TO-DO
 * @author Kris Box
 * Time: 19:08
 * Date: 28/01/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class FormatJSON(private val array: JSONObject?, private val context: Context) {

    fun basicFormat(): ArrayList<Program> {

        val innerArray: JSONArray = array!!.getJSONArray("results")
        val programmes = ArrayList<Program>()
        try {
            // Only returns first option

            for ( i in 0..(innerArray.length() - 1)) {
                val json = innerArray.getJSONObject(i)

                val string: Array<String> = arrayOf(json.getString("name"),
                        json.getString("first_air_date"),
                        json.getString("poster_path"),
                        json.getString("vote_average"))
                val program = Program(string, context)
                programmes.add(program)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return programmes
    }

}
