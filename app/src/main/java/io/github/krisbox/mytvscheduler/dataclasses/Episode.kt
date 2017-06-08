package io.github.krisbox.mytvscheduler.dataclasses

/**
 * Description: Episode to be shown in program view
 *
 * @author Kris Box
 * Time: 19:28
 * Date: 03/06/2017
 * @version 1.0
 * Copyright (c) Kris Box 2017
 */

class Episode(data: Array<String>) {
    var seasonNo = data[0]
    var episodeNo = data[1]
    var episodeName = data[2]
    var episodeRating = data[3]
    var episodeRelease = data[4]


    override fun toString() : String{
        return "Season :" + seasonNo + ", Episode: " + episodeNo + ", Name: " + episodeName
    }

}