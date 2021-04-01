package com.noahgeren.trailangel.ui.trails

import androidx.lifecycle.ViewModel
import com.noahgeren.trailangel.database.TrailRepository

class TrailsViewModel: ViewModel() {

    private val trailRepository = TrailRepository.get()

    fun trailsListLiveData(parkId: Int) = trailRepository.getTrailsByParkId(parkId)

}