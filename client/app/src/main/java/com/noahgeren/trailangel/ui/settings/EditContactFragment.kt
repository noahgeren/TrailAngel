package com.noahgeren.trailangel.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.database.EmergencyContactRepository
import com.noahgeren.trailangel.models.EmergencyContact
import com.noahgeren.trailangel.ui.common.Utils

class EditContactFragment: Fragment(R.layout.fragment_edit_contact) {

    private val args: EditContactFragmentArgs by navArgs()

    private lateinit var name: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var submit: Button
    private lateinit var contact: EmergencyContact

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.edit_contact_name)
        phoneNumber = view.findViewById(R.id.edit_contact_phone_number)
        submit = view.findViewById(R.id.edit_contact_save)

        contact = args.contact

        name.setText(contact.name)
        phoneNumber.setText(contact.phoneNumber)

        submit.setOnClickListener {
            Utils.hideKeyboard(requireActivity())
            EmergencyContactRepository.get().saveContact(EmergencyContact(contact.id, name.text.toString(), phoneNumber.text.toString(), contact.user))
            Navigation.findNavController(view).navigateUp()
        }

    }
}