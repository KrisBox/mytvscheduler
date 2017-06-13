package io.github.krisbox.mytvscheduler.searching

import android.content.Context
import io.github.krisbox.mytvscheduler.database.TVSchedulerDBInsert
import io.github.krisbox.mytvscheduler.dataclasses.Episode
import io.github.krisbox.mytvscheduler.dataclasses.Program
import java.util.ArrayList

/**
 * Description: Gets the searchData, and returns it as an ArrayList
 * @author Kris
 * Time: 22:47
 * Date: 18/04/2017
 * @version 1.0
 * Copyright (c) Kristofer Box 2017
 */

class Search(private val context: Context, private val query: String, private val id: String) {

    /**
     * Given the search string, it searches for the data on the webs
     * Returns an ArrayList of each Program
     */
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

    /**
     * Given the ID, it searches the webs for the specific program
     * Returns the result in a Program
     */
    val programData: Program
        get() {
            val data = GetData(query)
            data.setIDForProgram(id)
            data.makeProgramConnection()
            var info = data.getSearchData()
            if (info != null){
                val format = FormatJSON(info, context)
                val program = format.programFormat()

                val insertCacheProgram = TVSchedulerDBInsert(context)
                insertCacheProgram.insertProgram(program)
                insertCacheProgram.db.close()

                val insertCacheProgramInfo = TVSchedulerDBInsert(context)
                insertCacheProgramInfo.insertProgramInfo(program)
                insertCacheProgramInfo.db.close()
                return program
            }

            val program = Program(context)
            return program
        }

    /**
     * Given the ID, get all of the season information for that program,
     * Returns as an ArrayList of an ArrayList of Episodes
     */
    val episodeData: ArrayList<ArrayList<Episode>>
        get () {
            val data = GetData(query)
            val episodeData = ArrayList<ArrayList<Episode>>()
            for (i in 1..query.toInt()){
                data.setIDForEpisode(id, i.toString())
                data.makeEpisodesConnection()
                val info = data.getSearchData()
                if (info != null){
                    val format = FormatJSON(info, context)
                    val seasonEp = format.seasonFormat(id)
                    episodeData.add(seasonEp)

                }
            }

            return episodeData
        }

}
