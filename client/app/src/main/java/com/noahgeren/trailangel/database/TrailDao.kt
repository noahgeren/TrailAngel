package com.noahgeren.trailangel.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.noahgeren.trailangel.models.Trail

@Dao
interface TrailDao {

    @Query("select * from trail where parkId=:parkId")
    fun getTrailsByParkId(parkId: Int): LiveData<List<Trail>>

    @Query("select * from trail where id=:id")
    fun getTrailById(id: Int): LiveData<Trail>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrails(trails: List<Trail>)

}