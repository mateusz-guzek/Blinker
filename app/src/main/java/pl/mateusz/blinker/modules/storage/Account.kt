package pl.mateusz.blinker.modules.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO use shared preferences to keep track of the main account that is used to show all data in app
@Entity(tableName = "accounts")
data class Account(var email: String, var apiToken: String) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0


    var name: String = email


}

