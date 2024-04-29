package pl.mateusz.blinker.modules.network

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response


class BLHttpCaller(private val client: OkHttpClient) {

    //private val root_url = "https://panel-b.baselinker.com/"
    private val root_url = "http://10.0.2.2/"


    fun loginToBL(login: String, password: String, then: ((Response) -> Unit)) {
        val body = """
            {
                "login": "$login",
                "password": "$password",
                "lang": "pl",
                "redirect": ""
            }
        """.trimMargin()
        val request: Request = Request.Builder()
            .url("${root_url}login.php")
            .post(body.toRequestBody("application/x-www-form-urlencoded".toMediaType()))
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                then(response)
            }
            override fun onFailure(call: Call, e: java.io.IOException) {
                Log.e("LOGIN_ERROR", e.stackTraceToString())
            }
        })

    }

    fun getApiToken(then: ((String) -> Unit)) {

        val request: Request = Request.Builder()
            .url("${root_url}other_api_token.php")
            .get()
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                // if error is around here then change how parsing works or just invalid credentials
                // if token is empty then handle it in ui
                val token = BLApiTokenParser.parse(response)
                then(token)
            }
            override fun onFailure(call: Call, e: java.io.IOException) {
                Log.e("API_TOKEN_PAGE_ERROR", e.stackTraceToString())
            }
        })

    }

    fun LogoutOfBL() {

    }

}

