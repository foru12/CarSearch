package com.example.testpizza.ui.search

import androidx.lifecycle.LiveData
import com.example.testpizza.Data.data.DataCar
import com.example.testpizza.Data.Dao.DaoCar

class CarRepository(private val daoCar: DaoCar) {

    val allCar: LiveData<List<DataCar>> = daoCar.gelAllCar()




    fun insert(car: DataCar) {
        daoCar.insertCar(car)
    }
    fun getCarByName(userName: String): LiveData<List<DataCar>>{
        return daoCar.getNameAllCar(userName)
    }
    fun getSortCar(isAsc:Boolean): List<DataCar>{
        return  daoCar.getSortCar(isAsc)
    }

    fun getCarByMarka(price: Long?, years: Int?,filterMarka: String?,filterModel:String?,mileage:Long?):  List<DataCar>{
        return daoCar.getFilteredEntities(filterMarka,filterModel,years,price,mileage)
    }









}