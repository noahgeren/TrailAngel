package com.noahgeren.trailangel.ui.trails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.ui.common.SharedViewModel

class ParksFragment : Fragment(R.layout.fragment_parks) {

    private val sharedViewModel: SharedViewModel by viewModels({ requireActivity() })

    private lateinit var list: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(sharedViewModel.trailsState != SharedViewModel.PARKS) {
            gotoTrails()
        }
        list = view.findViewById(R.id.parks_recycler_view)
        val parkAdapter = ParkAdapter(context, listOf(
            "Big Bend", "Canyonlands", "Deathvalley", "Yosemite"
        ), { _: View, position: Int, _: Int ->
            Log.d("ParkFragment", "Clicked park #${position}")
            sharedViewModel.trailsState = SharedViewModel.TRAILS
            gotoTrails()
        })
        list.adapter = parkAdapter
    }

    private fun gotoTrails() {
        Navigation.findNavController(requireView()).navigate(R.id.action_parks_to_trails)
    }

}