package pl.mateusz.blinker.modules.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import pl.mateusz.blinker.modules.utils.SPValues.MAIN_ACCOUNT_ID


// For convenience
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()




// ======= Shared Preferences Section ==========================

private const val BASE_NAME: String = "BLINKER_SPREFS" // dont change

private object SPValues {
    val MAIN_ACCOUNT_ID = "main_acc_id"
}

fun getPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences(BASE_NAME, Context.MODE_PRIVATE)
}

// yeet
fun Context.setMainAccountID(id: Int) {


    val editor = getPrefs(this).edit()

    editor.putInt(MAIN_ACCOUNT_ID, id)
    editor.apply()
}


/*
    Returns -1 if doesn't exist
 */
fun Context.getMainAccountID(): Int {
    return getPrefs(this).getInt(MAIN_ACCOUNT_ID, -1)
}

// ======= End of Shared Preferences Section ==========================

