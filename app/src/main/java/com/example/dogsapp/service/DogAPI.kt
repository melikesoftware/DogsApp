package com.example.dogsapp.service

import com.example.dogsapp.model.Dog
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DogAPI {


    //https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json


    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<Dog>>
}

