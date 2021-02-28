package com.noahgeren.trailangel.ui.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R

class ScheduleAdapter(private val context: Context?, private val trails: List<List<String>>) : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(LayoutInflater.from(context).inflate(R.layout.row_schedule, parent, false))
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.date.text = trails[position][0]
        holder.startTime.text = trails[position][1]
        holder.duration.text = trails[position][2]
        holder.trailName.text = trails[position][3]
    }

    override fun getItemCount(): Int {
        return trails.size
    }
}

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val date: TextView = itemView.findViewById(R.id.schedule_date)
    val startTime: TextView = itemView.findViewById(R.id.schedule_start_time)
    val duration: TextView = itemView.findViewById(R.id.schedule_duration)
    val trailName: TextView = itemView.findViewById(R.id.schedule_trail_name)

}