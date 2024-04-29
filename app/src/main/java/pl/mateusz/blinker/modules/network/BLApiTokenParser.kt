package pl.mateusz.blinker.modules.network

import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class BLApiTokenParser {

    companion object {
        fun parse(httpReponse: Response): String {
            val html: String = httpReponse.body?.string() ?: ""
            val doc: Document = Jsoup.parse(html)

            // it just has to exist, ok?
            return doc.select("div.panel-body").text()
        }
    }
}