package com.noahgeren.trailangel.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noahgeren.trailangel.database.EmergencyContactRepository

class SettingsViewModel : ViewModel() {

    private val contactRepository = EmergencyContactRepository.get()

    val contactsLiveListData = contactRepository.getContacts()

    private val name by lazy {
        MutableLiveData("Noah Geren")
    }

    private val trailName by lazy {
        MutableLiveData("Trail Name")
    }

    fun getName(): LiveData<String> = name
    fun getTrailName(): LiveData<String> = trailName

    fun setTrailName(trailName: String) {
        this.trailName.apply {
            value = trailName
        }
    }

}