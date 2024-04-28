package pl.mateusz.blinker.modules.services

import okhttp3.OkHttpClient
import okhttp3.Response
import pl.mateusz.blinker.modules.network.BLApiCaller
import pl.mateusz.blinker.modules.network.BLHttpCaller
import pl.mateusz.blinker.modules.network.BLSessionCookieManager

// impostor service
object BLService {

    private val client: OkHttpClient
    private val cookies: BLSessionCookieManager

    private val httpCaller: BLHttpCaller
    private val apiCaller: BLApiCaller

    init {
        cookies = BLSessionCookieManager()

        client = OkHttpClient.Builder()
            .cookieJar(cookies)
            .build()

        httpCaller = BLHttpCaller(client)
        apiCaller = BLApiCaller(client)
    }

    fun login(login: String, password: String, then: ((Response) -> Unit)) {
        httpCaller.loginToBL(login, password, then)
    }

    fun getApiToken(then: ((String) -> Unit)) {
        httpCaller.getApiToken(then)
    }
}