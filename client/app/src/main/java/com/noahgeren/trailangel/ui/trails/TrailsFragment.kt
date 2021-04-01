package com.noahgeren.trailangel.ui.trails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Trail
import com.noahgeren.trailangel.ui.common.SharedViewModel

private const val TAG = "TrailsFragment"

class TrailsFragment : Fragment(R.layout.fragment_trails) {

    private val sharedViewModel: SharedViewModel by viewModels({ requireActivity() })
    private val trailsViewModel: TrailsViewModel by viewModels()
    private val args: TrailsFragmentArgs by navArgs()

    private lateinit var list: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(sharedViewModel.trailsState != SharedViewModel.TRAILS) {
            gotoTrailDetails(sharedViewModel.selectedTrail)
        }
        list = view.findViewById(R.id.trails_recycler_view)
        trailsViewModel.trailsListLiveData(args.parkId).observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(trails: List<Trail>) {
        list.adapter = TrailAdapter(trails)
    }

    private fun gotoTrailDetails(trailId: Int) {
        sharedViewModel.selectedTrail = trailId
        sharedViewModel.trailsState = SharedViewModel.TRAIL_DETAILS
        val action = TrailsFragmentDirections.actionTrailsToTrailDetails(trailId)
        Navigation.findNavController(requireView()).navigate(action)
    }

    inner class TrailAdapter(private val trails: List<Trail>) : RecyclerView.Adapter<TrailHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            TrailHolder(LayoutInflater.from(context).inflate(R.layout.row_trail, parent, false))

        override fun onBindViewHolder(holder: TrailHolder, position: Int) = holder.bind(trails[position])

        override fun getItemCount() = trails.size

    }

    inner class TrailHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private lateinit var trail: Trail

        private val trailName: TextView = itemView.findViewById(R.id.trail_name)
        private val trailLength: TextView = itemView.findViewById(R.id.trail_length)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(trail: Trail) {
            this.trail = trail
            trailName.text = trail.name
            trailLength.text = "${trail.length} mi"
        }

        override fun onClick(view: View?) = gotoTrailDetails(trail.id)

    }

}