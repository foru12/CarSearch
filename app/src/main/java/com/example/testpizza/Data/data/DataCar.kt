package com.example.testpizza.Data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car")
data class DataCar(

    @PrimaryKey (autoGenerate = true) val id:Int,
    val imageUrl: String,
    val modelCar: String,
    val markaCar: String,
    val yearsCar: Int,
    val priceCar:Long,
    val mileageCar:Long,
    val doorCar:Int,
    val engineCar:String,
    val colorCar:String,
    val cityCar:String,
    val user:String
)
