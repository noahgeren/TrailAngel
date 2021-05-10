package com.noahgeren.trailangel.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class EmergencyContact(
    @PrimaryKey var id: Long? = null,
    var name: String? = null,
    var phoneNumber: String? = null,
    var user: String? = null
): Serializable
