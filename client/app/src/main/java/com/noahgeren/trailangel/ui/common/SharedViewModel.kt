package com.noahgeren.trailangel.ui.common

import androidx.lifecycle.ViewModel
import com.noahgeren.trailangel.models.Trail

class SharedViewModel: ViewModel() {

    companion object {
        const val PARKS = 0
        const val TRAILS = 1
        const val TRAIL_DETAILS = 2
    }

    var trailsState = PARKS
    var selectedPark: Int = 0
    var selectedTrail: Trail? = null

}