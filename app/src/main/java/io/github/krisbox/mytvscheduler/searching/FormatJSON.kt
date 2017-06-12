package io.github.krisbox.mytvscheduler.searching


import android.content.Context
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.dataclasses.Program
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Description: Formats the JSONObject into the data required
 * @author Kris Box
 * Time: 19:08
 * Date: 28/01/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class FormatJSON(private val array: JSONObject?, private val context: Context) {

    /**
     * Formats the search data from the searchview
     * Adds to an array of 'Programs' and returns
     */
    fun searchFormat(): ArrayList<Program> {

        val innerArray: JSONArray = array!!.getJSONArray("results")
        val programmes = ArrayList<Program>()
        try {

            for ( i in 0..(innerArray.length() - 1)) {
                val json = innerArray.getJSONObject(i)

                val string: Array<String> = arrayOf(json.getString("name"),
                        json.getString("first_air_date"),
                        json.getString("poster_path"),
                        json.getString("vote_average"),
                        json.getString("id"))
                val program = Program(context)
                program.giveSearchData(string)
                programmes.add(program)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return programmes
    }

    /**
     * Formats the JSON for the program view
     * Adds to an 'Program' object and returns.
     */
    fun programFormat(): Program {
        val program = Program(context)
        try {
            val string: Array<String> = arrayOf(array!!.getString("name"),
                    array.getString("first_air_date"),
                    array.getString("poster_path"),
                    array.getString("vote_average"),
                    array.getString("id"),
                    array.getString("overview"),
                    array.getString("backdrop_path"),
                    array.getString("number_of_seasons"))
            program.giveProgramData(string)
        } catch (e: JSONException){
            e.printStackTrace()
        }

        return program
    }

    /**
     * Formats the information for the Episode View
     * Adds to an array of 'Episodes' and returns
     */
    fun seasonFormat(): ArrayList<Episode> {
        val episodes = ArrayList<Episode>()

        val innerArray: JSONArray = array!!.getJSONArray("episodes")
        try {

            for ( i in 0..(innerArray.length() - 1)) {
                val json = innerArray.getJSONObject(i)

                val string: Array<String> = arrayOf(json.getString("season_number"),
                        json.getString("episode_number"),
                        json.getString("name"),
                        json.getString("vote_average"),
                        json.getString("air_date"))
                val episode = Episode(string)
                episodes.add(episode)
            }

        } catch (e: JSONException){
            e.printStackTrace()
        }


        return episodes
    }

}
