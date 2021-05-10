package com.noahgeren.trailangel.ui.schedule

import androidx.lifecycle.ViewModel
import com.noahgeren.trailangel.database.HikeRepository

class ScheduleViewModel : ViewModel() {

    private val hikeRepository = HikeRepository.get()

    val hikesListLiveData = hikeRepository.getHikes()

}