package pl.mateusz.blinker.modules.storage.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
    Nie powinienem używać Room Database'a do tego bo można łatwo wykraść te dane chyba
 */
@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(account: Account)

    @Delete
    fun deleteAccount(account: Account)


    // i had plans for app to be able to keep track of multiple baselinker accounts
    // these plans were fullfilled
    @Query("SELECT * from accounts")
    fun getAllAccounts(): Array<Account>

    @Query("SELECT * from accounts WHERE email = :mail ")
    fun getAccountByMail(mail: String): Account?

    @Query("SELECT * from accounts WHERE name = :name ")
    fun getAccountByName(name: String): Account?
}