package com.noahgeren.trailangel.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private lateinit var list: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.schedule_recycler_view)
        val scheduleAdapter = ScheduleAdapter(context, listOf(
            listOf("Friday Feb 26", "7:30 AM", "14 HR", "Half Dome"),
            listOf("Monday Mar 1", "12:00 PM", "5 HR", "Bridalveil Falls"),
            listOf("Saturday Apr 24", "8:00 AM", "3 HR", "Horseshoe Trail"),
            listOf("Saturday Apr 24", "1:00 PM", "5 HR", "Wildflower Trail"),
            listOf("Saturday Apr 25", "9:00 PM", "2 HR", "Firefalls")
        ))
        list.adapter = scheduleAdapter
    }

}