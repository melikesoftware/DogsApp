package com.example.dogsapp.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Dog(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val dogName: String?,
    @ColumnInfo(name = "bred_for")
    @SerializedName("bred_for")
    val dogBredFor: String?,
    @ColumnInfo(name = "breed_group")
    @SerializedName("breed_group")
    val dogBreedGroup: String?,
    @ColumnInfo(name = "life_span")
    @SerializedName("life_span")
    val lifeSpan: String?,
    @ColumnInfo(name = "origin")
    @SerializedName("origin")
    val origin: String?,
    @ColumnInfo(name = "temperament")
    @SerializedName("temperament")
    val temperament: String?,
    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url:String?
    ){

    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}


