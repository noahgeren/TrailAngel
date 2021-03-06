package com.noahgeren.trailangel.ui.trails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Trail
import com.noahgeren.trailangel.ui.common.Utils.onClick

class TrailAdapter(private val context: Context?, private val trails: List<Trail>,
                  private val itemClickListener: (view: View, position: Int, type: Int) -> Unit) : RecyclerView.Adapter<TrailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailViewHolder {
        val viewHolder = TrailViewHolder(LayoutInflater.from(context).inflate(R.layout.row_trail, parent, false))
        viewHolder.onClick(itemClickListener)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TrailViewHolder, position: Int) {
        val trail = trails[position]
        holder.trailName.text = trail.name
        holder.trailLength.text = "${trail.length} mi"
    }

    override fun getItemCount(): Int {
        return trails.size
    }

}

class TrailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val trailName: TextView = itemView.findViewById(R.id.trail_name)
    val trailLength: TextView = itemView.findViewById(R.id.trail_length)

}