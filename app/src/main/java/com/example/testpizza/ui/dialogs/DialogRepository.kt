package com.example.testpizza.ui.dialogs

import androidx.lifecycle.LiveData
import com.example.testpizza.Data.data.NamesMarka
import com.example.testpizza.Data.Dao.DaoCarMarks
import com.example.testpizza.Data.Dao.DaoCarModel
import com.example.testpizza.Data.data.NamesModel

class DialogRepository(
    private val daoCarMarks: DaoCarMarks,
    private val daoCarModel: DaoCarModel
) {


    fun getAllMarksName(): LiveData<List<NamesMarka>> {
        return daoCarMarks.getNamesMarks()
    }

    fun getAllModelByMarka(carMarka:String): List<NamesModel> {
        return daoCarModel.getNameModelCar(carMarka)
    }


}






