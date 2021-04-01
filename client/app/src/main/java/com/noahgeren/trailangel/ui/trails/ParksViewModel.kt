package com.noahgeren.trailangel.ui.trails

import androidx.lifecycle.ViewModel
import com.noahgeren.trailangel.database.ParkRepository

class ParksViewModel: ViewModel() {

    private val parkRepository = ParkRepository.get()

    val parksListLiveData = parkRepository.getParks()

}