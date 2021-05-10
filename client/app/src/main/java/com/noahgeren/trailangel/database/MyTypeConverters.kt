package com.noahgeren.trailangel.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDateTime

class MyTypeConverters {

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDateTime(formatted: String?): LocalDateTime? {
        return LocalDateTime.parse(formatted)
    }

}