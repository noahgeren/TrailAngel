package com.noahgeren.trailangel.ui.schedule

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R

class ScheduleFragment : Fragment(R.layout.schedule_fragment) {

    private val viewModel: ScheduleViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ScheduleViewModel::class.java)
    }
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.schedule_recycler_view)
        val scheduleAdapter = ScheduleAdapter(context, listOf(
                listOf("Friday Feb 26", "7:30 AM", "14 HR", "Half Dome"),
                listOf("Monday Mar 1", "12:00 PM", "5 HR", "Bridalveil Falls"),
                listOf("Friday Feb 26", "7:30 AM", "14 HR", "Half Dome"),
                listOf("Monday Mar 1", "12:00 PM", "5 HR", "Bridalveil Falls"),
                listOf("Friday Feb 26", "7:30 AM", "14 HR", "Half Dome"),
                listOf("Monday Mar 1", "12:00 PM", "5 HR", "Bridalveil Falls"),
                listOf("Friday Feb 26", "7:30 AM", "14 HR", "Half Dome"),
                listOf("Monday Mar 1", "12:00 PM", "5 HR", "Bridalveil Falls"),
        ))
        recyclerView.adapter = scheduleAdapter
    }

}