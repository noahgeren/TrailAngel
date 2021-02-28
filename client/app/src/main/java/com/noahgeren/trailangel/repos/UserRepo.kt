package com.noahgeren.trailangel.repos

object UserRepo {

    var loggedIn = false // TODO: Change this

    fun login(): Boolean {
        loggedIn = true
        return loggedIn
    }

    fun logout() {
        loggedIn = false
    }

}