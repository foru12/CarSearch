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
import com.example.testpizza.Data.Dao.DaoCar
import com.example.testpizza.Data.data.DataCar
import com.example.testpizza.Data.data.DataCarModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [DataCar::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBaseCar : RoomDatabase() {

    abstract fun carDao(): DaoCar


    private class CarDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {


        @RequiresApi(Build.VERSION_CODES.O)
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabaseCar(database.carDao())
                }
            }
        }

        suspend fun populateDatabaseCar(carDao: DaoCar) {
            val listCar = arrayListOf<DataCar>(
                DataCar(
                    0,
                    "",
                    "Q1",
                    "Audi",
                    2017,
                    2000000,
                    110000,
                    5,
                    "600",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Q3",
                    "Audi",
                    2022,
                    5000000,
                    5000,
                    5,
                    "660",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Q6",
                    "Audi",
                    2018,
                    4500000,
                    20000,
                    5,
                    "220",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "M2",
                    "BMW",
                    2015,
                    3500000,
                    62498,
                    5,
                    "180",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "M3",
                    "BMW",
                    2023,
                    6000000,
                    60000,
                    5,
                    "600",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "K5",
                    "Kia",
                    2020,
                    2000000,
                    30000,
                    5,
                    "180",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Rio",
                    "Kia",
                    2019,
                    900000,
                    190000,
                    5,
                    "120",
                    "Черный",
                    "Москва",
                    ""
                ),
                DataCar(
                    0,
                    "",
                    "Rio",
                    "Kia",
                    2016,
                    1000000,
                    20000,
                    5,
                    "120",
                    "Черный",
                    "Москва",
                    ""
                ),
                DataCar(
                    0,
                    "",
                    "Q1",
                    "Audi",
                    2017,
                    2000000,
                    110000,
                    5,
                    "600",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Q3",
                    "Audi",
                    2022,
                    5000000,
                    5000,
                    5,
                    "660",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Q6",
                    "Audi",
                    2018,
                    4500000,
                    20000,
                    5,
                    "220",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "M2",
                    "BMW",
                    2015,
                    3500000,
                    62498,
                    5,
                    "180",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "M3",
                    "BMW",
                    2023,
                    6000000,
                    60000,
                    5,
                    "600",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "K5",
                    "Kia",
                    2020,
                    2000000,
                    30000,
                    5,
                    "180",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Rio",
                    "Kia",
                    2019,
                    900000,
                    190000,
                    5,
                    "120",
                    "Черный",
                    "Москва",
                    ""
                ),
                DataCar(
                    0,
                    "",
                    "Rio",
                    "Kia",
                    2016,
                    1000000,
                    20000,
                    5,
                    "120",
                    "Черный",
                    "Москва",
                    ""
                ),
                DataCar(
                    0,
                    "",
                    "Q1",
                    "Audi",
                    2017,
                    2000000,
                    110000,
                    5,
                    "600",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Q3",
                    "Audi",
                    2022,
                    5000000,
                    5000,
                    5,
                    "660",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Q6",
                    "Audi",
                    2018,
                    4500000,
                    20000,
                    5,
                    "220",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "M2",
                    "BMW",
                    2015,
                    3500000,
                    62498,
                    5,
                    "180",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "M3",
                    "BMW",
                    2023,
                    6000000,
                    60000,
                    5,
                    "600",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "K5",
                    "Kia",
                    2020,
                    2000000,
                    30000,
                    5,
                    "180",
                    "Черный",
                    "Москва",
                    ""

                ),
                DataCar(
                    0,
                    "",
                    "Rio",
                    "Kia",
                    2019,
                    900000,
                    190000,
                    5,
                    "120",
                    "Черный",
                    "Москва",
                    ""
                ),
                DataCar(
                    0,
                    "",
                    "Rio",
                    "Kia",
                    2016,
                    1000000,
                    20000,
                    5,
                    "120",
                    "Черный",
                    "Москва",
                    ""
                )

            )
            for (i in listCar) {
                carDao.insertCar(i)
            }

        }


    }

    companion object {
        private var INSTANCE: AppDataBaseCar? = null

        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): AppDataBaseCar? {

            if (INSTANCE == null) {
                synchronized(AppDataBaseCar::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBaseCar::class.java, "car.db"
                    ).allowMainThreadQueries()
                        .addCallback(CarDatabaseCallback(scope))
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