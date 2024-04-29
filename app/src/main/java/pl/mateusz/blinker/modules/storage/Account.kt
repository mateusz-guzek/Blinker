package pl.mateusz.blinker.modules.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

// i should
@Entity(tableName = "accounts")
data class Account(var email: String, var apiToken: String) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0


    var isMain: Boolean = false

    var name: String = ""


}

