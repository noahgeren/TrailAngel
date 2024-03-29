package com.noahgeren.trailangel.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Trail(
    @PrimaryKey val id: Int = 0,
    val name: String? = null,
    val parkId: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val length: Double? = null,
    val hikeTime: Double? = null,
    val description: String? = null
): Serializable
