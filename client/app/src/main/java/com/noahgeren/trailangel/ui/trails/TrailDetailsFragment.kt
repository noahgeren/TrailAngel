package com.noahgeren.trailangel.ui.trails

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Hike
import java.time.LocalDateTime
import kotlin.math.floor

class TrailDetailsFragment: Fragment(R.layout.fragment_trail_details) {

    private val args: TrailDetailsFragmentArgs by navArgs()

    private lateinit var trailName: TextView
    private lateinit var startingPoint: TextView
    private lateinit var length: TextView
    private lateinit var hikeTime: TextView
    private lateinit var description: TextView
    private lateinit var schedule: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trailName = view.findViewById(R.id.trail_details_name)
        startingPoint = view.findViewById(R.id.trail_details_start)
        length = view.findViewById(R.id.trail_details_length)
        hikeTime = view.findViewById(R.id.trail_details_hike_time)
        description = view.findViewById(R.id.trail_details_description)
        schedule = view.findViewById(R.id.trail_details_schedule)

        val trail = args.trail

        trailName.text = trail.name
        startingPoint.text = "${trail.latitude}, ${trail.longitude}"
        length.text = "${trail.length} mi"
        hikeTime.text = "${trail.hikeTime} hrs"
        description.text = trail.description

        schedule.setOnClickListener {
            val startTime = LocalDateTime.now()
            val endTime = if(trail.hikeTime != null) {
                startTime.plusHours(floor(trail.hikeTime).toLong()).plusMinutes((trail.hikeTime % 1).toLong())
            } else {
                startTime.plusHours(1)
            }
            val action = TrailDetailsFragmentDirections.actionNavigationTrailDetailsToScheduleTrailFragment(
                Hike(null, trail.name ?: "", trail.latitude, trail.longitude, trail.length, startTime, endTime, null)
            )
            Navigation.findNavController(requireView()).navigate(action)
        }
    }
}