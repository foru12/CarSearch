package com.example.testpizza.Data.data

import androidx.room.ColumnInfo

data class NamesModel (
    @ColumnInfo(name = "modelName")
    var namesModel: String? = null
)