package io.github.krisbox.mytvscheduler.searching

import org.json.JSONObject
import java.io.*

import java.net.URL
import java.net.URLConnection

/**
 * Description: This is the main file that gets the searchData from the website, depending on what the user
 * searched for.
 * @author Kris
 * Time: 19:54
 * Date: 09/01/2017
 * @version 2.0 : Changed for the new API website (TheMovieDB)
 * Copyright (c) Kris Box 2017
 */
class GetData(private val programmeName: String) {

    private var searchURL = "https://api.themoviedb.org/3/search/tv"
    private var idURL = "https://api.themoviedb.org/3/tv/"
    private var seasonsURL = "https://api.themoviedb.org/3/tv/"
    private var api = "?api_key=c8f357a17874949380defddba226a59b"
    private var dataRetrieve: JSONObject? = null
    private var connection: URLConnection? = null
    private var programID: String? = null

    init {
        searchURL += api
        searchURL += "&query=" + this.programmeName

    }

    /**
     * Makes a connection for when the User searches for a program
     */
    fun makeSearchConnection() {
        try {
            connection = URL(searchURL).openConnection()
        } catch (e: Exception) {
            println("Couldn't make a connection")
        }

    }

    /**
     * Makes a connection for the connection view
     */
    fun makeProgramConnection(){
        try {
            connection = URL(idURL).openConnection()
        } catch (e: Exception){
            println("Couldn't make connection")
        }
    }

    /**
     * Makes a connection for the episode view
     */
    fun makeEpisodesConnection(){
        try {
            connection = URL(seasonsURL).openConnection()
        } catch (e: Exception){
            println("Couldn't make connection")
        }
    }

    /**
     * Gets the information from the internet given the url
     */
    fun getSearchData(): JSONObject? {
        val thread = Thread(Runnable {
            try {
                val response = connection!!.getInputStream()
                val streamReader = BufferedReader(InputStreamReader(response, "UTF-8"))
                val responseStrBuilder = StringBuilder()
                do {
                    val inputStr = streamReader.readLine()
                    responseStrBuilder.append(inputStr)
                } while (inputStr != null)
                dataRetrieve = JSONObject(responseStrBuilder.toString())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
        }

        return dataRetrieve

    }

    /**
     * Gives the ID for the selected program (Program View)
     */
    fun setIDForProgram(id: String){
        programID = id

        idURL += programID
        idURL += api
    }

    /**
     * Gives the ID for the season number (Episode View)
     */
    fun setIDForEpisode(id: String, seasonNo: String){
        programID = id
        seasonsURL = "https://api.themoviedb.org/3/tv/"

        seasonsURL += programID
        seasonsURL += "/season/"
        seasonsURL += seasonNo
        seasonsURL += api
    }


}
