package com.noahgeren.trailangel.ui.trails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Trail
import com.noahgeren.trailangel.ui.common.SharedViewModel

class TrailsFragment : Fragment(R.layout.fragment_trails) {

    private val sharedViewModel: SharedViewModel by viewModels({ requireActivity() })

    private lateinit var list: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(sharedViewModel.trailsState != SharedViewModel.TRAILS) {
            gotoTrailDetails()
        }
        list = view.findViewById(R.id.trails_recycler_view)
        val trailAdapter = TrailAdapter(context, listOf(
                Trail("Half Dome", 15.1),
                Trail("Bridalveil Falls", 4.5)
        )) { _: View, position: Int, _: Int ->
            Log.d("TrailFragment", "Clicked trail #${position}")
            sharedViewModel.trailsState = SharedViewModel.TRAIL_DETAILS
            gotoTrailDetails()
        }
        list.adapter = trailAdapter
    }

    private fun gotoTrailDetails() {
        Navigation.findNavController(requireView()).navigate(R.id.action_trails_to_trail_details)
    }

}