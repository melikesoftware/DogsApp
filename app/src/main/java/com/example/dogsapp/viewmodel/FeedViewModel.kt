package com.example.dogsapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import com.example.dogsapp.model.Dog

import com.example.dogsapp.service.DogAPIService
import com.example.dogsapp.service.DogDatabase
import com.example.dogsapp.util.DogSharedPreferences
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


class FeedViewModel(application: Application): BaseViewModel(application) {
    private val dogAPIService= DogAPIService()
    private val compositeDisposable= CompositeDisposable()
    private var sharedPreferences= DogSharedPreferences(getApplication())
    private var refreshTime=10*60*1000*1000*1000L


    val dogs= MutableLiveData<List<Dog>>()
    val dogLoading= MutableLiveData<Boolean>()



    fun refreshData(){
        val updateTime=sharedPreferences.getTime()
        if(updateTime!=null && updateTime !=0L && System.nanoTime()-updateTime<refreshTime){
            getDataFromSQLite()
        }
        else{
            getDataFromAPI()
        }




    }
    private fun getDataFromSQLite(){
        dogLoading.value=true
        launch {
            val dogs_list= DogDatabase(getApplication()).dogDao().getAllDogs()

            showDogData(dogs_list)
            Toast.makeText(getApplication(),"Dogs from SQLite", Toast.LENGTH_LONG).show()
        }
    }

     fun refreshFromAPI(){

        getDataFromAPI()
    }
    private fun getDataFromAPI(){
        dogLoading.value=true

       compositeDisposable.add(
           dogAPIService.getData()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(object : DisposableSingleObserver<List<Dog>>(){
                   override fun onSuccess(t: List<Dog>) {


                       storeInSQLite(t)
                       Toast.makeText(getApplication(),"Dogs from API", Toast.LENGTH_LONG).show()

                   }

                   override fun onError(e: Throwable) {


                       dogLoading.value=false
                       e.printStackTrace()

                   }

               })

       )

    }

    private fun showDogData(dogList: List<Dog>){

        dogs.value=dogList.take(50)
        dogLoading.value=false
    }
    private fun storeInSQLite(list: List<Dog>){

        launch {
            val dao= DogDatabase(getApplication()).dogDao()
            dao.deleteAllData()
            val listLong=dao.insertAll(*list.toTypedArray())
            var i=0
            while (i<list.size){
                list[i].id=listLong[i].toInt()
                i=i+1
            }
            showDogData(list)


        }

        sharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}