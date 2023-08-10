package com.example.testpizza.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testpizza.Data.data.DataCar
import com.example.testpizza.Data.db.AppDataBaseCar
import com.example.testpizza.ui.search.CarRepository
import kotlinx.coroutines.launch

class AddViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: CarRepository
    val allCarByUser: LiveData<List<DataCar>>
    init {
        val carDao = AppDataBaseCar.getInstance(application,viewModelScope)!!.carDao()
        repository = CarRepository(carDao)
        allCarByUser = repository.getCarByName("me")

    }

    fun insert(car: DataCar) = viewModelScope.launch {
        repository.insert(car)
    }



}