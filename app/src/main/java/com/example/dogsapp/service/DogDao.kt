package com.example.dogsapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dogsapp.model.Dog

@Dao
interface DogDao {

    @Insert
    suspend fun  insertAll(vararg dogs:Dog): List<Long>

    @Query("SELECT  * FROM  dog ")
    suspend fun getAllDogs(): List<Dog>

    @Query("SELECT * FROM  dog WHERE  id= :dogId")
    suspend fun getDog(dogId:Int):Dog



    @Query("DELETE FROM dog")
    suspend fun deleteAllData()
}