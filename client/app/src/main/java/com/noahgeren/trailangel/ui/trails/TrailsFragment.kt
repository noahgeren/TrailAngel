package com.noahgeren.trailangel.ui.trails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R

class TrailsFragment : Fragment(R.layout.trails_fragment) {

    private lateinit var parksRecyclerView: RecyclerView

    private val viewModel: TrailsViewModel by lazy {
        ViewModelProvider(requireActivity()).get(TrailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parksRecyclerView = view.findViewById(R.id.trails_parks_recycler_view)
        val parkAdapter = ParkAdapter(context, listOf(
                "Big Bend", "Canyonlands", "Deathvalley", "Yosemite",
                "Big Bend", "Canyonlands", "Deathvalley", "Yosemite",
                "Big Bend", "Canyonlands", "Deathvalley", "Yosemite",
                "Big Bend", "Canyonlands", "Deathvalley", "Yosemite",
        ))
        parksRecyclerView.adapter = parkAdapter
    }

}