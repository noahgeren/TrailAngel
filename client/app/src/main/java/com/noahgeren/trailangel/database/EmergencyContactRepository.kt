package com.noahgeren.trailangel.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.models.EmergencyContact
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class EmergencyContactRepository private constructor(context: Context) {

    init {
        RoomDatabase.build(context)
    }

    private val contactDao: EmergencyContactDao = RoomDatabase.get().emergencyContactDao()
    private val executor = Executors.newSingleThreadExecutor()
    private var contactsLoaded = false

    fun getContacts(): LiveData<List<EmergencyContact>> {
        if(!contactsLoaded) {
            ApiService.getContacts()
            contactsLoaded = true
        }
        return contactDao.getEmergencyContacts()
    }

    fun insertContacts(contacts: List<EmergencyContact>) = executor.execute {
        contactDao.insertEmergencyContacts(contacts)
    }

    fun saveContact(contact: EmergencyContact) {
        ApiService.saveContact(contact)
    }

    fun insertContact(contact: EmergencyContact) = executor.execute {
        contactDao.insertEmergencyContact(contact)
    }

    companion object {
        private var INSTANCE: EmergencyContactRepository? = null
        fun init(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = EmergencyContactRepository(context)
            }
        }
        fun get(): EmergencyContactRepository {
            return INSTANCE ?: throw IllegalStateException("EmergencyContactRepository must be initialized.")
        }
    }

}