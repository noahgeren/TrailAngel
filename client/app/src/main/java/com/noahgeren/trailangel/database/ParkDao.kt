package com.noahgeren.trailangel.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.noahgeren.trailangel.models.Park

@Dao
interface ParkDao {

    @Query("select * from park")
    fun getParks(): LiveData<List<Park>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertParks(parks: List<Park>)

}