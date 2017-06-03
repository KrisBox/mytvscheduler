package io.github.krisbox.mytvscheduler.fragments.Program

import android.app.Fragment
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.krisbox.mytvscheduler.R
import io.github.krisbox.mytvscheduler.Search

/**
 * Description: Shows the information of a program given an ID
 * @author Kris
 * Time: 14:41
 * Date: 02/06/2017
 * Last Updated: 02/06/2017
 * Copyright (c) Kristofer Box 2017
 */

class ProgramInformationFragment(internal var id: String, internal var context: Context) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.program_information, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val program = Search(context, "", id).programData

        val title = view?.findViewById(R.id.title) as TextView
        title.text = program.programName

        val backdrop = view.findViewById(R.id.backdrop) as ImageView
        backdrop.setImageBitmap(Bitmap.createScaledBitmap(program.programBackdrop, 400, 220, false))

        val date = view.findViewById(R.id.date) as TextView
        date.text = program.programRelease

        val rating = view.findViewById(R.id.imdbRating) as TextView
        rating.text = program.programRating

        val overview = view.findViewById(R.id.overview) as TextView
        overview.text = program.programOverview

    }

}