package io.github.krisbox.mytvscheduler.jsonparsing

import org.json.JSONObject
import java.io.*

import java.net.URL
import java.net.URLConnection

/**
 * Description: This is the main file that gets the searchData from the website, depending on what the
 * searched for.
 * @author Kris
 * Time: 19:54
 * Date: 09/01/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */
class GetData(private val programmeName: String) {

    private var searchURL = "https://api.themoviedb.org/3/search/tv"
    private var idURL = "https://api.themoviedb.org/3/tv/"
    private var api = "?api_key=c8f357a17874949380defddba226a59b"
    private var dataRetrieve: JSONObject? = null
    private var connection: URLConnection? = null
    private var programID: String? = null

    init {
        searchURL += api
        searchURL += "&query=" + this.programmeName

    }

    fun makeSearchConnection() {
        try {
            connection = URL(searchURL).openConnection()
        } catch (e: Exception) {
            println("Couldn't make a connection")
        }

    }

    fun makeProgramConnection(){
        try {
            connection = URL(idURL).openConnection()
        } catch (e: Exception){
            println("Couldn't make connection")
        }
    }

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

    fun setID(id: String){
        programID = id

        idURL += programID
        idURL += api
    }


}
