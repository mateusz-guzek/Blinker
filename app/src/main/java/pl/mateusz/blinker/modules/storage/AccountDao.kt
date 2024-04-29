package pl.mateusz.blinker.modules.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/*
    Nie powinienem używać Room Database'a do tego bo można łatwo wykraść te dane chyba
 */
@Dao
interface AccountDao {

    @Insert
    fun insertAccount(account: Account)


    // i have plans for app to be able to keep track of multiple baselinker accounts
    @Query("SELECT * from accounts")
    fun getAllAccounts(): Array<Account>

    @Query("SELECT * from accounts WHERE email = :mail ")
    fun getAccountByMail(mail: String): Account?

    @Query("SELECT * from accounts WHERE name = :name ")
    fun getAccountByName(name: String): Account?
}