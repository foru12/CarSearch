package com.example.testpizza.Data.db

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testpizza.Data.Converter
import com.example.testpizza.Data.Dao.DaoCarMarks
import com.example.testpizza.Data.Dao.DaoCarModel
import com.example.testpizza.Data.data.DataCarMarka
import com.example.testpizza.Data.data.DataCarModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [DataCarMarka::class,DataCarModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDataBaseCarAdd : RoomDatabase() {

    //Можно сделать заполнение данных через JSON

    abstract fun carMarks(): DaoCarMarks
    abstract fun carModel(): DaoCarModel
    //abstract fun carMarks() : DaoCarMarks


    private class CarDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {


        @RequiresApi(Build.VERSION_CODES.O)
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            AppDataBaseCarAdd.INSTANCE?.let { database ->
                scope.launch {
                    populateDatabaseCarMarks(database.carMarks())
                    populateDatabaseCarModel(database.carModel())


                }
            }
        }

        suspend private fun populateDatabaseCarModel(daoCarModel: DaoCarModel) {

            val listModel = arrayListOf<DataCarModel>(
                DataCarModel(1, "Audi", "A4"),
                DataCarModel(2, "Audi", "Q1"),
                DataCarModel(3, "Audi", "Q2"),
                DataCarModel(4, "Audi", "Q3"),
                DataCarModel(5, "Audi", "Q4"),
                DataCarModel(6, "Audi", "Q5"),
                DataCarModel(7, "Audi", "Q6"),
                DataCarModel(8, "BMW", "M1"),
                DataCarModel(9, "BMW", "M2"),
                DataCarModel(10, "BMW", "M3"),
                DataCarModel(11, "BMW", "X1"),
                DataCarModel(12, "BMW", "X2"),
                DataCarModel(13, "BMW", "X3"),
                DataCarModel(14, "BMW", "X4"),
                DataCarModel(15, "BMW", "X5"),
                DataCarModel(16, "BMW", "X6"),
                DataCarModel(17, "BMW", "X7"),
                DataCarModel(18, "Mercedes", "C"),
                DataCarModel(19, "Mercedes", "E"),
                DataCarModel(20, "Mercedes", "S"),
                DataCarModel(21, "Mercedes", "M"),
                DataCarModel(22, "Mercedes", "V"),
                DataCarModel(23, "Mercedes", "GLS"),
                DataCarModel(24, "Mercedes", "GLE"),
                DataCarModel(25, "Mercedes", "GLC"),
                DataCarModel(26, "Mercedes", "AMG"),
                DataCarModel(27, "Kia", "Optima"),
                DataCarModel(28, "Kia", "Carnival"),
                DataCarModel(29, "Kia", "Ceed"),
                DataCarModel(30, "Kia", "Cerato"),
                DataCarModel(31, "Kia", "K5"),
                DataCarModel(32, "Kia", "Rio"),
                DataCarModel(33, "Kia", "Seltos"),
                DataCarModel(34, "Kia", "Sorento"),
                DataCarModel(35, "Kia", "Soul"),
                DataCarModel(36, "Kia", "Sportage"),
                DataCarModel(37, "Ford", "EcoSport"),
                DataCarModel(38, "Ford", "Escape"),
                DataCarModel(39, "Ford", "Explorer"),
                DataCarModel(40, "Ford", "F-150"),
                DataCarModel(41, "Ford", "Fiesta"),
                DataCarModel(42, "Ford", "Focus"),
                DataCarModel(43, "Ford", "Mondeo"),
                DataCarModel(44, "Ford", "Mustang"),


                )

            for (i in listModel) {
                daoCarModel.insertCarModel(i)
            }


        }

        suspend private fun populateDatabaseCarMarks(daoCarMarks: DaoCarMarks) {


            val listkMarka = arrayListOf<DataCarMarka>(
                DataCarMarka(1, "Audi"),
                DataCarMarka(2, "BMW"),
                DataCarMarka(3, "Mercedes"),
                DataCarMarka(4, "Kia"),
                DataCarMarka(5, "Ford"),
                )
            for (i in listkMarka) {
                daoCarMarks.insertCarMarks(i)
            }


        }


    }

    companion object {
        private var INSTANCE: AppDataBaseCarAdd? = null

        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): AppDataBaseCarAdd? {

            if (INSTANCE == null) {
                synchronized(AppDataBaseCar::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBaseCarAdd::class.java, "carMarks.db"

                    ).allowMainThreadQueries()
                        .addCallback(AppDataBaseCarAdd.CarDatabaseCallback(scope))
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


}