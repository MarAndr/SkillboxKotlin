package module22

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException

class MovieRepository {

    fun searchMovie(searchText: String,
                    year: String, type: String?,
                    callback: (List<RemoteMovie>) -> Unit,
                    errorCallback: (e: Throwable) -> Unit,
                    emptyListCallback: (Boolean) -> Unit){
        Thread{
            try {
                val response = Network.getSearchMovieCall(searchText, year, type).execute()
                val responseString = response.body()?.string().orEmpty()
                val movieList = parseMovieResponse(responseString)
                Log.d("MyServer", "response string = $responseString ")
                Log.d("MyServer", "response is successful = ${response.isSuccessful} ")
                callback(movieList)
                errorCallback(Throwable("network"))
                if (responseString.contains("Movie not found!")){
                    emptyListCallback(true)
                } else {
                    emptyListCallback(false)
                }

            } catch (e: IOException) {
                Log.d("MyServer", "execute request error = ${e.message}", e)
                callback(emptyList())
                errorCallback(Throwable("no network"))

            }
            Thread.sleep(1000)
        }.start()

    }

    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie>{
        return try {
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(Search::class.java).nonNull()
            val movies = adapter.fromJson(responseBodyString)?.search
            movies!!
        } catch (e: Exception) {
            Log.d("MyServer", "parse response error = ${e.message}", e)
            emptyList()
        }
    }
}