package com.example.testpizza.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testpizza.Data.data.DataCarYears

@Dao
interface DaoCarYears {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarYears(carYears: List<DataCarYears>)

    //@Query("Select * from CarYears where modelName = :id")
    @Query("Select * from CarYears")
    fun getAllYearsCar(): LiveData<List<DataCarYears>>

    @Query("DELETE from CarYears")
    suspend fun deleteCarYears()


}