package com.noahgeren.trailangel.ui.trails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R

class ParkAdapter(private val context: Context?, private val parks: List<String>) : RecyclerView.Adapter<ParkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        return ParkViewHolder(LayoutInflater.from(context).inflate(R.layout.trails_parks_row, parent, false))
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        holder.parkName.text = parks[position]
    }

    override fun getItemCount(): Int {
        return parks.size
    }
}

class ParkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val parkName: TextView = itemView.findViewById(R.id.trails_park_name)

}