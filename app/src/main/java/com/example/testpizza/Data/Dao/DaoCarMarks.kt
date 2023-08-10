package com.example.testpizza.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testpizza.Data.data.DataCarMarka
import com.example.testpizza.Data.data.NamesMarka

@Dao
interface DaoCarMarks {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarMarks(carMarks: DataCarMarka)



    @Query("SELECT markaName from CarMarka")
    fun getNamesMarks(): LiveData< List<NamesMarka>>


    @Query("DELETE from CarMarka")
    fun deleteCarMarks()
}