package com.noahgeren.trailangel.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.noahgeren.trailangel.models.Hike

@Dao
interface HikeDao {

    @Query("select * from hike")
    fun getHikes(): LiveData<List<Hike>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHikes(hikes: List<Hike>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHike(hike: Hike)
}