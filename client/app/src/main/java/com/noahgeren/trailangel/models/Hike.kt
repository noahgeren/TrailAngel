package com.noahgeren.trailangel.models

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime

@Entity
@RequiresApi(Build.VERSION_CODES.O)
data class Hike (
    @PrimaryKey var id: Int? = null,
    var trailName: String = "",
    var latitude: Double? = null,
    var longitude: Double? = null,
    var length: Double? = null,
    var startTime: LocalDateTime = LocalDateTime.now(),
    var endTime: LocalDateTime = LocalDateTime.now(),
    var user: String? = null
): Serializable
