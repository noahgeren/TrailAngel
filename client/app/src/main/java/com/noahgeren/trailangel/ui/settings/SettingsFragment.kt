package com.noahgeren.trailangel.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Layout
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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.models.EmergencyContact
import com.noahgeren.trailangel.models.User
import com.noahgeren.trailangel.ui.MainActivity
import com.noahgeren.trailangel.ui.common.PreferenceUtils
import com.noahgeren.trailangel.ui.common.Utils
import com.noahgeren.trailangel.ui.login.LoginActivity
import com.noahgeren.trailangel.ui.trails.ParksFragment


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

        nameText.text = PreferenceUtils.getName()
        trailNameText.text = PreferenceUtils.getTrailName()

        changeTrailName.setOnClickListener {
            val alertView = LayoutInflater.from(context).inflate(R.layout.alert_change_trail_name, view as ViewGroup, false)
            val alertInput: EditText = alertView.findViewById(R.id.alert_trail_name)
            Utils.showAlert(requireContext(), "Change Trail Name", null, alertView, "Save", "Cancel",
                { _, _ ->
                    ApiService.updateAccount(User("", PreferenceUtils.getName(), alertInput.text.toString()))
                    trailNameText.text = alertInput.text.toString()
                },
                {dialog, _ -> dialog.cancel()})
        }

        viewModel.contactsLiveListData.observe(viewLifecycleOwner) {
            contacts.adapter = ContactAdapter(it)
            addContact.visibility = if (it.size >= 3) View.GONE else View.VISIBLE
        }

        addContact.setOnClickListener {
            val action = SettingsFragmentDirections.actionNavigationSettingsToEditContactFragment(EmergencyContact(null, "", "", ""))
            Navigation.findNavController(view).navigate(action)
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
    }

    fun editContact(contact: EmergencyContact) {
        val action = SettingsFragmentDirections.actionNavigationSettingsToEditContactFragment(contact)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private inner class ContactAdapter(val contacts: List<EmergencyContact>) : RecyclerView.Adapter<ContactHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ContactHolder(LayoutInflater.from(context).inflate(R.layout.row_contact, parent, false))

        override fun onBindViewHolder(holder: ContactHolder, position: Int) {
            holder.bind(contacts[position])
        }

        override fun getItemCount() = contacts.size

    }

    private inner class ContactHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var contact: EmergencyContact
        private val contactName: TextView = view.findViewById(R.id.contact_name)
        private val edit: Button = view.findViewById(R.id.contact_edit)

        init {
            edit.setOnClickListener(this)
        }

        fun bind(contact: EmergencyContact) {
            this.contact = contact
            contactName.text = contact.name
        }

        override fun onClick(view: View?) {
            editContact(contact)
        }

    }

}