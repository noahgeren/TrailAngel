package com.noahgeren.trailangel.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Contact

class EmergencyContactAdapter(private val context: Context?, private val contacts: LiveData<MutableList<Contact>>, private val contactModel: ContactModel) : RecyclerView.Adapter<EmergencyContactViewHolder>() {

    init {
        contacts.observeForever {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyContactViewHolder {
        return EmergencyContactViewHolder(LayoutInflater.from(context).inflate(R.layout.emergency_contact_row, parent, false))
    }

    override fun onBindViewHolder(holder: EmergencyContactViewHolder, position: Int) {
        holder.name.text = contacts.value?.get(position)?.name ?: "Error"
        holder.edit.setOnClickListener {
            contactModel.removeContactAt(position)
        }
    }

    override fun getItemCount(): Int {
        return contacts.value?.size ?: 0
    }

}

class EmergencyContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var name: TextView = itemView.findViewById(R.id.emergency_contact_name)
    var edit: Button = itemView.findViewById(R.id.emergency_contact_edit)

}

