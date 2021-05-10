package com.noahgeren.trailangel.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.noahgeren.trailangel.models.EmergencyContact

@Dao
interface EmergencyContactDao {

    @Query("select * from emergencycontact")
    fun getEmergencyContacts(): LiveData<List<EmergencyContact>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmergencyContacts(contacts: List<EmergencyContact>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmergencyContact(contact: EmergencyContact)

}