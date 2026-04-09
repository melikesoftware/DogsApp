package com.example.dogsapp.util


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class DogSharedPreferences {

    companion object{
        private var sharedPreferences: SharedPreferences?=null
        private val preferencesTime="PREFERENCE_TIME"
        @Volatile private var instance: DogSharedPreferences?=null
        private val lock=Any()

        operator fun invoke(context: Context): DogSharedPreferences=instance?:synchronized (lock){
            instance?:makeSharedPreferences(context).also {
                instance=it
            }
        }


        private fun makeSharedPreferences(context: Context): DogSharedPreferences{
            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context)
            return DogSharedPreferences()

        }



    }
    fun saveTime(time:Long){
        sharedPreferences?.edit()?.putLong(preferencesTime,time)?.commit()
    }

    fun getTime()=sharedPreferences?.getLong(preferencesTime,0)
}


