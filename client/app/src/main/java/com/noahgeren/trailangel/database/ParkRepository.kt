package com.noahgeren.trailangel.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.models.Park
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "fuck-this-stupid-shit"

class ParkRepository private constructor(context: Context){

    init {
        RoomDatabase.build(context)
    }

    private val parkDao = RoomDatabase.get().parkDao()
    private val executor = Executors.newSingleThreadExecutor()
    private var parksLoaded = false

    fun getParks(): LiveData<List<Park>> {
        if(!parksLoaded) { // TODO: Add caching
            ApiService.getParks()
            parksLoaded = true
        }
        return parkDao.getParks()
    }

    fun insertParks(parks: List<Park>) = executor.execute {
        parkDao.insertParks(parks)
    }

    companion object {
        private var INSTANCE: ParkRepository? = null

        fun init(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = ParkRepository(context)
            }
        }
        fun get(): ParkRepository {
            return INSTANCE ?: throw IllegalStateException("ParkRepository must be initialized.")
        }

    }

}