package pl.mateusz.blinker.modules.network

import okhttp3.OkHttpClient


/*
1. save session token to room database then also fetch api token
2. if any request to the website itself doesnt return the right response then repeat step 1.

TODO api calls are in insomnia
 */
object BLHttpCaller {   // is this singleton

    private val client = OkHttpClient()

    fun LoginToBL(login: String, password: String) {


    }

    fun LogoutOfBL() {

    }
}

