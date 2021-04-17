package com.noahgeren.trailangel.models

import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime

data class Hike(
    @PrimaryKey val id: Int? = null,
    val trailName: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val length: Double? = null,
    val startTime: LocalDateTime? = null,
    val endTime: LocalDateTime? = null
): Serializable
