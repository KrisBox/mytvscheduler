package io.github.krisbox.mytvscheduler.searching

import android.content.Context
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.dataclasses.Program
import java.util.ArrayList

/**
 * Description: Gets the searchData, and returns it as an ArrayList
 * @author Kris
 * Time: 22:47
 * Date: 18/04/2017
 * Last Updated: 18/04/2017
 * Copyright (c) Kristofer Box 2017
 */

class Search(private val context: Context, private val query: String, private val id: String) {

    // Return arraylist
    val searchData: ArrayList<Program>
        get() {
            val data = GetData(query)
            data.makeSearchConnection()
            var info = data.getSearchData()
            if (info != null) {
                val format = FormatJSON(info, context)
                val programmes = format.searchFormat()
                return programmes
            }

            val programmes = ArrayList<Program>()
            return programmes
        }

    val programData: Program
        get() {
            val data = GetData(query)
            data.setIDForProgram(id)
            data.makeProgramConnection()
            var info = data.getSearchData()
            if (info != null){
                val format = FormatJSON(info, context)
                val program = format.programFormat()
                return program
            }

            val program = Program(context)
            return program
        }

    val episodeData: ArrayList<ArrayList<Episode>>
        get () {
            val data = GetData(query)
            val episodeData = ArrayList<ArrayList<Episode>>()
            for (i in 1..query.toInt()){
                data.setIDForEpisode(id, i.toString())
                data.makeEpisodesConnection()
                var info = data.getSearchData()
                if (info != null){
                    val format = FormatJSON(info, context)
                    val seasonEp = format.seasonFormat()
                    episodeData.add(seasonEp)

                }
            }

            return episodeData
        }

}
