package com.example.testpizza.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testpizza.Data.db.AppDataBaseCar
import com.example.testpizza.Data.data.DataCar
import kotlinx.coroutines.launch

class SearchViewModel (application: Application): AndroidViewModel(application){
    private val repository: CarRepository
    val allCar: LiveData<List<DataCar>>
    init {
        val carDao = AppDataBaseCar.getInstance(application,viewModelScope)!!.carDao()
        repository = CarRepository(carDao)
        allCar = repository.allCar

    }

    fun insert(car: DataCar) = viewModelScope.launch {
        repository.insert(car)
    }

    fun sort(isAsc:Boolean) : List<DataCar> {
        lateinit var listSortCar : List<DataCar>
        viewModelScope.launch {
            listSortCar = repository.getSortCar(isAsc)
        }
        return listSortCar

    }


    fun filter(price: Long?, years: Int?, filterMarka: String?, filterModel: String?, mileage:Long?): List<DataCar>?{
         var listFilterCar : List<DataCar>? = null
        viewModelScope.launch {
            listFilterCar = repository.getCarByMarka(price,years,filterMarka,filterModel,mileage)
        }
        return  listFilterCar
    }
}