package pl.mateusz.blinker.modules.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mateusz.blinker.modules.storage.models.Account
import pl.mateusz.blinker.modules.storage.models.AccountDao

@Database(entities = [Account::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accounts(): AccountDao
}