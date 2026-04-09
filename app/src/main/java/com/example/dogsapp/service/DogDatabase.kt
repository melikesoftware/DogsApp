package com.example.dogsapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.dogsapp.model.Dog


@Database(entities = [Dog::class], version = 1)

abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao


    companion object{
        @Volatile private var instance: DogDatabase?=null

        private val lock=Any()
        operator  fun invoke(context: Context)=instance?:synchronized(lock){
            instance?:makeDatabase(context).also {
               instance=it
            }
        }


        private fun makeDatabase(context: Context)=Room.databaseBuilder(context.applicationContext,
            DogDatabase::class.java,"dogDatabase").build()
    }
}
