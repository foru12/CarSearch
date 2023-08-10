package com.example.testpizza.Data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CarModel")

data class DataCarModel(
    @PrimaryKey val id:Int,
    val markaName: String,
    val modelName: String,

    )
