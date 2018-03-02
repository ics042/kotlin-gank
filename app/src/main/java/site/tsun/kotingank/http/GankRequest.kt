package site.tsun.kotingank.http

import com.google.gson.Gson
import site.tsun.kotingank.model.GankResult
import java.net.URL


class GankRequest {
    companion object {
        private val BASE_URL = "http://gank.io/api/data/"
    }

    fun getGankData(category: String, page: Int): GankResult {
        val url = BASE_URL + category + "/10/" + page
        val gankDataStr = URL(url).readText()
        return Gson().fromJson(gankDataStr, GankResult::class.java)
    }
}