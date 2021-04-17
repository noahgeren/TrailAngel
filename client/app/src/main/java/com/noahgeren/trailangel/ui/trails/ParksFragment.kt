package com.noahgeren.trailangel.ui.trails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Park
import com.noahgeren.trailangel.ui.common.ListItemSelectedCallback
import com.noahgeren.trailangel.ui.common.SharedViewModel

private const val TAG = "ParksFragment"

class ParksFragment : Fragment(R.layout.fragment_parks) {

    private val sharedViewModel: SharedViewModel by viewModels({ requireActivity() })
    private val parksViewModel: ParksViewModel by viewModels()

    private lateinit var list: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(sharedViewModel.trailsState != SharedViewModel.PARKS) {
            gotoTrails(sharedViewModel.selectedPark)
        }
        list = view.findViewById(R.id.parks_recycler_view)

        parksViewModel.parksListLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(parks: List<Park>) {
        list.adapter = ParkAdapter(parks)
    }

    private fun gotoTrails(parkId: Int) {
        sharedViewModel.selectedPark = parkId
        val action = ParksFragmentDirections.actionParksToTrails(parkId)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private inner class ParkAdapter(val parks: List<Park>) : RecyclerView.Adapter<ParkHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ParkHolder(LayoutInflater.from(context).inflate(R.layout.row_park, parent, false))

        override fun onBindViewHolder(holder: ParkHolder, position: Int) = holder.bind(parks[position])

        override fun getItemCount() = parks.size
    }

    private inner class ParkHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var park: Park
        private val parkName: TextView = itemView.findViewById(R.id.park_name)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(park: Park) {
            this.park = park
            parkName.text = park.name
        }
        override fun onClick(v: View?) {
            sharedViewModel.trailsState = SharedViewModel.TRAILS
            gotoTrails(park.id)
        }
    }

}