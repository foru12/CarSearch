package com.example.testpizza.Data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CarYears")
data class DataCarYears(
    @PrimaryKey val id:Int,
    val years: String,
    val modelName: String
)