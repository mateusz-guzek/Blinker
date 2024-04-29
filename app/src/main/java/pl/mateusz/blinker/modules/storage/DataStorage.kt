package pl.mateusz.blinker.modules.storage

import android.content.Context
import androidx.room.Room

private val dbName = "blinker-db"

class DataStorage private constructor(context: Context) {

    companion object {
        private var instance: DataStorage? = null


        /**
         * Singleton dla uposledzonych, albo poprostu nowy pattern wymyslilem "Kicked Singleton"
         * DESIGN: Bo inaczej nie umiem i mi sie nie chce myslec, mam dosc tego Rooma
        **/


        // call this only once
        // Kicked
        fun initialize(context: Context) {
            if (instance == null) {
                instance = DataStorage(context)
            }
        }

        // Singleton
        fun getInstance(): DataStorage {
            if (instance == null) {
                throw NullPointerException("Object not initialized")
            }
            return instance as DataStorage
        }

    }


    val db: AppDatabase

    init {
            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                dbName
            ).build()
    }


}