package com.example.testpizza.ui.dialogs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testpizza.Data.data.DataCar
import com.example.testpizza.Data.data.NamesMarka
import com.example.testpizza.Data.data.NamesModel
import com.example.testpizza.Data.db.AppDataBaseCar
import com.example.testpizza.Data.db.AppDataBaseCarAdd
import com.example.testpizza.ui.search.CarRepository
import kotlinx.coroutines.launch

class DialogViewModel (application: Application): AndroidViewModel(application) {

    private val repository: DialogRepository
    private val carRepository: CarRepository
    val allCarMarka: LiveData<List<NamesMarka>>



    init {
        val carDaoMarks = AppDataBaseCarAdd.getInstance(application,viewModelScope)!!.carMarks()
        val carDaoModel = AppDataBaseCarAdd.getInstance(application,viewModelScope)!!.carModel()
        val carDao = AppDataBaseCar.getInstance(application,viewModelScope)!!.carDao()
        repository = DialogRepository(carDaoMarks,carDaoModel)
        carRepository = CarRepository(carDao)
        allCarMarka = repository.getAllMarksName()
    }

    fun getDataModel(pos:Int): List<NamesModel> {
        lateinit var allModel: List<NamesModel>
        viewModelScope.launch {
            allModel = repository.getAllModelByMarka(allCarMarka.value?.get(pos)?.namesMarka.toString())
        }
        return allModel
    }

    fun insert(carDataCar: DataCar) = viewModelScope.launch {
        carRepository.insert(
            carDataCar
        )
    }









}