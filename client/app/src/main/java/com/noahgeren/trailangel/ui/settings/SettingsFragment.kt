package com.noahgeren.trailangel.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Contact
import com.noahgeren.trailangel.ui.MainActivity
import com.noahgeren.trailangel.ui.common.ContactAdapter
import com.noahgeren.trailangel.ui.common.PreferenceUtils
import com.noahgeren.trailangel.ui.common.Utils
import com.noahgeren.trailangel.ui.login.LoginActivity


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels({requireActivity()})

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
        Utils.setBasicTextObserver(mapOf(
            viewModel.getName() to nameText,
            viewModel.getTrailName() to trailNameText,
        ), viewLifecycleOwner)

        changeTrailName.setOnClickListener {
            val alertView = LayoutInflater.from(context).inflate(R.layout.alert_change_trail_name, view as ViewGroup, false)
            val alertInput: EditText = alertView.findViewById(R.id.alert_trail_name)
            Utils.showAlert(requireContext(), "Change Trail Name", null, alertView, "Save", "Cancel",
                { _, _ -> viewModel.setTrailName(alertInput.text.toString()) },
                {dialog, _ -> dialog.cancel()})
        }

        val contactsAdapter = ContactAdapter(context, viewModel.getContacts(), viewModel)
        viewModel.getContacts().observe(viewLifecycleOwner) {
            contactsAdapter.notifyDataSetChanged()
        }
        contacts.adapter = contactsAdapter

        addContact.setOnClickListener {
            viewModel.addContact(Contact("Test Tester", "+1-417-123-4567"))
        }

        logout.setOnClickListener {
            Utils.showAlert(requireContext(), "Logout", "Are you sure you want to logout?", null, "Yes", "No",
                { _, _ ->
                    PreferenceUtils.setToken(null)
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    requireActivity().finish()
                    startActivity(intent)
                },
                { _, _ -> /* TODO */ })
        }

        viewModel.getContacts().observe(viewLifecycleOwner, { addContact.visibility = if (it.size >= 3) View.GONE else View.VISIBLE })
    }

}