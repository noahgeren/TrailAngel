package com.noahgeren.trailangel.ui.login.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.noahgeren.trailangel.repos.UserRepo

class SplashViewModel: ViewModel() {

    var loggedIn = liveData {
        emit(UserRepo.loggedIn)
    }

}