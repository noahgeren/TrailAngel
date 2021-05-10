package com.noahgeren.trailangel.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.models.Trail
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "fuck-this-stupid-shit"

class TrailRepository private constructor(context: Context) {

    init {
        RoomDatabase.build(context)
    }

    private val trailDao = RoomDatabase.get().trailDao()
    private val executor = Executors.newSingleThreadExecutor()
    private var trailsLoaded = false

    fun getTrailsByParkId(parkId: Int): LiveData<List<Trail>> {
        if(!trailsLoaded) { // TODO: Add caching
            ApiService.getTrails()
            trailsLoaded = true
        }
        return trailDao.getTrailsByParkId(parkId)
    }

    fun insertTrails(trails: List<Trail>) = executor.execute {
        trailDao.insertTrails(trails)
    }

    companion object {
        private var INSTANCE: TrailRepository? = null

        fun init(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = TrailRepository(context)
            }
        }
        fun get(): TrailRepository {
            return INSTANCE ?: throw IllegalStateException("TrailRepository must be initialized")
        }
    }

}