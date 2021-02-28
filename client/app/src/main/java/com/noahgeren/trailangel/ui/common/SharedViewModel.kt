package com.noahgeren.trailangel.ui.common

import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    companion object {
        const val PARKS = 0; const val TRAILS = 1
    }

    var trailsState = PARKS

}