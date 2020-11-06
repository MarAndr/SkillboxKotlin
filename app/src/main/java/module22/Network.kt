package module22

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object Network {

//    Добавьте Okhttp interceptor, который для всех запросов добавляет API key в
//    query «Параметры». Избавьтесь от установки API key при формировании
//    запроса. Добавьте interceptor для OkHttpClient.

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(
            CustomApiInterceptor("apikey", "8bd228a8")
        )
        .build()

    fun getSearchMovieCall(movieName: String, year: String, type: String?): Call{
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("s", movieName)
            .addQueryParameter("type", type)
            .addQueryParameter("y", year)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }


}