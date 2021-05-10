package com.noahgeren.trailangel.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noahgeren.trailangel.database.EmergencyContactRepository

class SettingsViewModel : ViewModel() {

    private val contactRepository = EmergencyContactRepository.get()

    val contactsLiveListData = contactRepository.getContacts()

}