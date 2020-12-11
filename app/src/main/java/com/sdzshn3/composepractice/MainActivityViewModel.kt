package com.sdzshn3.composepractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val _string = MutableLiveData<String>()
    val string: LiveData<String> = _string

    fun changeString(string: String) {
        _string.value = string
    }
}