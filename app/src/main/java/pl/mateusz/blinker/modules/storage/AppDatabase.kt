package pl.mateusz.blinker.modules.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAccountDao(): AccountDao
}