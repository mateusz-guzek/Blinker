package pl.mateusz.blinker.modules.network

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl


class BLSessionCookieManager : CookieJar {

    private val storage: ArrayList<Cookie> = ArrayList()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {

        // Remove expired Cookies
        storage.removeIf {cookie ->
            cookie.expiresAt < System.currentTimeMillis()
        }
        //storage.removeIf(cookie -> cookie.expiresAt() < System.currentTimeMillis())

        // Only return matching Cookies
        return storage.filter { cookie ->
            cookie.matches(url)
        }
    }
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        for (newCookie in cookies) {
            val existingCookie = storage.find {
                it.name == newCookie.name && it.domain == newCookie.domain
            }
            if (existingCookie != null) {
                storage.remove(existingCookie)
            }
            storage.add(newCookie)

        }
    }
}
