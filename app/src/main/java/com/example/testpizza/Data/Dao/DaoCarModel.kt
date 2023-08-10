package com.example.testpizza.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testpizza.Data.data.DataCarModel
import com.example.testpizza.Data.data.NamesModel

@Dao
interface DaoCarModel {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarModel(carModel: DataCarModel)

    @Query("Select modelName from CarModel Where markaName = :markaName")
    fun getNameModelCar(markaName: String): List<NamesModel>

    @Query("DELETE from CarModel")
    suspend fun deleteCarModel()
}