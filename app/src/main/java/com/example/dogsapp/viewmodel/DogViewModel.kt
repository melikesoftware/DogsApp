package com.example.dogsapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData

import com.example.dogsapp.model.Dog

import com.example.dogsapp.service.DogDatabase
import kotlinx.coroutines.launch

class DogViewModel(application: Application): BaseViewModel(application) {
    val dogLiveData= MutableLiveData<Dog>()

    fun getFromData(id:Int){


        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            val dog=dao.getDog(id)
            dogLiveData.value =dog
        }


    }
}