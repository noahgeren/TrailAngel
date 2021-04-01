package com.noahgeren.trailangel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noahgeren.trailangel.models.Trail

@Database(entities = [Trail::class], version = 2)
abstract class TrailDatabase: RoomDatabase() {

    abstract fun trailDao(): TrailDao

}