package com.noahgeren.trailangel.ui.common

import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    companion object {
        const val PARKS = 0
        const val TRAILS = 1
        const val TRAIL_DETAILS = 3
    }

    var trailsState = PARKS
    var selectedPark: Int = 0
    var selectedTrail: Int = 0

}