package com.noahgeren.trailangel.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.models.Hike
import com.noahgeren.trailangel.models.Park
import java.lang.IllegalStateException
import java.util.concurrent.Executors



class HikeRepository private constructor(context: Context) {

    init {
        RoomDatabase.build(context)
    }

    private val hikeDao = RoomDatabase.get().hikeDao()
    private val executor = Executors.newSingleThreadExecutor()
    private var hikesLoaded = false

    fun getHikes(): LiveData<List<Hike>> {
        if(!hikesLoaded) { // TODO: Add caching
            ApiService.getHikes()
            hikesLoaded = true
        }
        return hikeDao.getHikes()
    }

    fun insertHikes(hikes: List<Hike>) = executor.execute {
        hikeDao.insertHikes(hikes)
    }

    fun saveHike(hike: Hike) {
        ApiService.saveHike(hike)
    }

    fun insertHike(hike: Hike) = executor.execute {
        hikeDao.insertHike(hike)
    }

    companion object {
        private var INSTANCE: HikeRepository? = null

        fun init(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = HikeRepository(context)
            }
        }
        fun get(): HikeRepository {
            return INSTANCE ?: throw IllegalStateException("HikeRepository must be initialized.")
        }
    }
}