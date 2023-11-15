package com.example.kotlinpract1.UI.StateHolder.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpract1.Data.Repository.DataRepository

class MyViewModel : ViewModel() {
    private val dataRepository = DataRepository()
    val data: LiveData<String> = dataRepository.getData()
}