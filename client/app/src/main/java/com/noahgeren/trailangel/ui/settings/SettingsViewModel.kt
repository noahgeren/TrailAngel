package com.noahgeren.trailangel.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noahgeren.trailangel.models.Contact
import com.noahgeren.trailangel.ui.common.ContactViewModel

class SettingsViewModel : ViewModel(), ContactViewModel {

    private val name by lazy {
        MutableLiveData("Noah Geren")
    }

    private val trailName by lazy {
        MutableLiveData("Trail Name")
    }

    private val contacts by lazy {
        MutableLiveData(mutableListOf(
            Contact("John Adams", "+1-123-456-7890"),
            Contact("Austin Powers", "+1-123-456-7890")
        ))
    }

    fun getName(): LiveData<String> = name
    fun getTrailName(): LiveData<String> = trailName
    fun getContacts(): LiveData<MutableList<Contact>> = contacts

    fun setTrailName(trailName: String) {
        this.trailName.apply {
            value = trailName
        }
    }

    override fun addContact(contact: Contact) {
        val newValue = contacts.value ?: mutableListOf()
        newValue.add(contact)
        contacts.value = newValue
    }

    override fun removeContactAt(index: Int) {
        val newValue = contacts.value ?: return
        newValue.removeAt(index)
        contacts.value = newValue
    }

}