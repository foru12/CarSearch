package com.example.testpizza.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.testpizza.Data.data.DataCar

@Dao
interface DaoCar {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(cars: DataCar)

    @Query("Select * from car")
    fun gelAllCar(): LiveData<List<DataCar>>

    @Query("Select * from car Where user = :userName")
    fun getNameAllCar(userName: String): LiveData<List<DataCar>>

    @Update
    fun updateCar(cars: DataCar)

    @Delete
    fun deleteCar(cars: DataCar)


    @Query("SELECT * FROM car ORDER BY CASE WHEN :isAsc = 1 THEN priceCar END ASC, CASE WHEN :isAsc = 0 THEN priceCar END DESC")
    fun getSortCar(isAsc: Boolean): List<DataCar>

    @Query("SELECT * from car WHERE markaCar LIKE :filterMarka  AND  modelCar LIKE :filterModel ")
    fun getItemsFiltered(filterMarka: String, filterModel: String): List<DataCar>
//    @Query("SELECT  * FROM car where markaCar>=:filterMarka LIKE :filterMarka ORDER BY markaCar")

    @Query("SELECT * from car WHERE markaCar LIKE :filterMarka  AND  modelCar LIKE :filterModel ")
    fun getFilteredAB(filterMarka: String, filterModel: String): List<DataCar>


    @Query("SELECT * FROM car WHERE markaCar = :filterMarka AND (modelCar = :filterModel OR modelCar IS NULL)")
    fun asfdwefgewf(filterMarka: String?, filterModel: String?): List<DataCar?>?


    @Query("SELECT * FROM car WHERE (:filterMarka IS NULL OR markaCar = :filterMarka) AND (:filterModel IS NULL OR modelCar = :filterModel) AND (:years IS NULL OR yearsCar = :years)  AND (:price IS NULL OR priceCar = :price) AND (:mileageCar IS NULL OR mileageCar = :mileageCar)")
    fun getFilteredEntities(
        filterMarka: String?,
        filterModel: String?,
        years: Int?,
        price: Long?,
        mileageCar: Long?
    ): List<DataCar>

}