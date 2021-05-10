package com.noahgeren.trailangel.ui.schedule

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Hike
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private lateinit var list: RecyclerView
    private val scheduleViewModel: ScheduleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.schedule_recycler_view)
        scheduleViewModel.hikesListLiveData.observe(viewLifecycleOwner, { hikes ->
            val scheduleAdapter = ScheduleAdapter(hikes.sortedBy { it.startTime })
            list.adapter = scheduleAdapter
        })
    }

    fun editHike(hike: Hike) {
        val action = ScheduleFragmentDirections.actionNavigationScheduleToScheduleTrailFragment(hike)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private inner class ScheduleAdapter(private val hikes: List<Hike>) : RecyclerView.Adapter<ScheduleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
            return ScheduleViewHolder(LayoutInflater.from(context).inflate(R.layout.row_schedule, parent, false))
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
            holder.bind(hikes[position], if(position > 0) hikes[position - 1] else null)
        }

        override fun getItemCount()= hikes.size
    }

    private inner class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        @RequiresApi(Build.VERSION_CODES.O)
        private val dateFormatter = DateTimeFormatter.ofPattern("EEEE MMMM d")
        @RequiresApi(Build.VERSION_CODES.O)
        private val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

        private lateinit var hike: Hike

        private val date: TextView = itemView.findViewById(R.id.schedule_date)
        private val startTime: TextView = itemView.findViewById(R.id.schedule_start_time)
        private val duration: TextView = itemView.findViewById(R.id.schedule_duration)
        private val trailName: TextView = itemView.findViewById(R.id.schedule_trail_name)

        init {
            itemView.setOnClickListener(this)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(hike: Hike, previous: Hike?) {
            this.hike = hike
            date.text = hike.startTime.format(dateFormatter)
            startTime.text = hike.startTime.format(timeFormatter)
            duration.text = "${hike.startTime.until(hike.endTime, ChronoUnit.HOURS)} HR"
            trailName.text = hike.trailName
            if(previous != null) {
                if(previous.startTime.toLocalDate().isEqual(hike.startTime.toLocalDate())) {
                    date.visibility = View.GONE
                }
            }
        }

        override fun onClick(view: View?) {
            editHike(hike)
        }

    }

}