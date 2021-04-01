package com.noahgeren.trailangel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noahgeren.trailangel.models.Park
import com.noahgeren.trailangel.models.Trail

@Database(entities = [Park::class], version = 1)
abstract class ParkDatabase: RoomDatabase() {

    abstract fun parkDao(): ParkDao

}