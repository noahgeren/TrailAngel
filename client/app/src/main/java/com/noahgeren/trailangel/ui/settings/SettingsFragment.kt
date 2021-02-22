package com.noahgeren.trailangel.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Contact
import com.noahgeren.trailangel.ui.common.AlertBuilder
import com.noahgeren.trailangel.ui.common.EmergencyContactAdapter


class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private val viewModel: SettingsViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)
    }

    private lateinit var nameText: TextView
    private lateinit var trailNameText: TextView
    private lateinit var contacts: RecyclerView
    private lateinit var changeTrailName: Button
    private lateinit var addContact: Button
    private lateinit var logout: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameText = view.findViewById(R.id.settings_name)
        trailNameText = view.findViewById(R.id.settings_trail_name)
        contacts = view.findViewById(R.id.settings_contacts)
        changeTrailName = view.findViewById(R.id.settings_change_trail_name)
        addContact = view.findViewById(R.id.settings_add_contact)
        logout = view.findViewById(R.id.settings_logout)
        setBasicTextObserver(mapOf(
                viewModel.getName() to nameText,
                viewModel.getTrailName() to trailNameText,
        ))

        changeTrailName.setOnClickListener {
            val alertView = LayoutInflater.from(context).inflate(R.layout.settings_alert_change_trail_name, view as ViewGroup, false)
            val alertInput: EditText = alertView.findViewById(R.id.settings_alert_trail_name)
            AlertBuilder.show(requireContext(), "Change Trail Name", null, alertView, "Save", "Cancel",
                    { _, _ -> viewModel.setTrailName(alertInput.text.toString()) },
                    {dialog, _ -> dialog.cancel()})
        }

        val contactsAdapter = EmergencyContactAdapter(context, viewModel.getContacts(), viewModel)
        contacts.adapter = contactsAdapter

        addContact.setOnClickListener {
            viewModel.addContact(Contact("Test Tester", "+1-417-123-4567"))
        }

        logout.setOnClickListener {
            AlertBuilder.show(requireContext(), "Logout", "Are you sure you want to logout?", null, "Yes", "No",
                    { _, _ -> /* TODO */ },
                    { _, _ -> /* TODO */ })
        }

        viewModel.getContacts().observe(viewLifecycleOwner, { addContact.visibility = if (it.size >= 3) View.GONE else View.VISIBLE })
    }

    fun setBasicTextObserver(textViewMap: Map<LiveData<String>, TextView>) {
        textViewMap.forEach {
            it.key.observe(viewLifecycleOwner, { value -> it.value.text = value })
        }
    }

}