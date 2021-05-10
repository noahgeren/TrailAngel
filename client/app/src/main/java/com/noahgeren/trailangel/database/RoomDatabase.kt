package com.noahgeren.trailangel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import com.noahgeren.trailangel.models.EmergencyContact
import com.noahgeren.trailangel.models.Hike
import com.noahgeren.trailangel.models.Park
import com.noahgeren.trailangel.models.Trail
import java.lang.IllegalStateException

private const val DATABASE_NAME = "trail-angel-database"

@Database(entities = [Park::class, Trail::class, Hike::class, EmergencyContact::class], version = 1, exportSchema = false)
@TypeConverters(MyTypeConverters::class)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun parkDao(): ParkDao
    abstract fun trailDao(): TrailDao
    abstract fun hikeDao(): HikeDao
    abstract fun emergencyContactDao(): EmergencyContactDao

    companion object {
        private var database: RoomDatabase? = null
        fun build(context: Context) {
            if(database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
        }
        fun get() : RoomDatabase {
            return database ?: throw IllegalStateException("RoomDatabase must be initialized.")
        }
    }
}