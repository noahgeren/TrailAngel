package com.noahgeren.trailangel.ui.trails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.ui.common.Utils.onClick

class ParkAdapter(private val context: Context?, private val parks: List<String>,
                  private val itemClickListener: (view: View, position: Int, type: Int) -> Unit) : RecyclerView.Adapter<ParkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val viewHolder = ParkViewHolder(LayoutInflater.from(context).inflate(R.layout.row_park, parent, false))
        viewHolder.onClick(itemClickListener)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        holder.parkName.text = parks[position]
    }

    override fun getItemCount(): Int {
        return parks.size
    }

}

class ParkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val parkName: TextView = itemView.findViewById(R.id.park_name)

}