package com.example.testpizza.Data.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "CarMarka")
data class DataCarMarka(
    @PrimaryKey val id: Int,
    val markaName : String,
    )