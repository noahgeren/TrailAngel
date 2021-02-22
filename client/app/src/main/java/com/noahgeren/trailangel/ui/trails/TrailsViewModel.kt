package com.noahgeren.trailangel.ui.trails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrailsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is the trails fragment"
    }
    val text: LiveData<String> = _text
}