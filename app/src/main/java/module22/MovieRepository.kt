package module22

import android.util.Log
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MovieRepository {

    companion object {
        private const val TAG = "MovieRepository"
    }

    fun searchMovie(
        searchText: String,
        year: String, type: String?,
        callback: (List<RemoteMovie>) -> Unit
    ) {
        Network.getSearchMovieCall(searchText, year, type).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(emptyList())
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseString = response.body()?.string().orEmpty()
                    val movieList = parseMovieResponse(responseString)
                    callback(movieList)
                }
            }
        })
    }

    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie> {
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

    fun changeData(
        movies: List<RemoteMovie>,
        source: String?,
        value: String?,
        index: Int,
        callback: (List<RemoteMovie>) -> Unit
    ) {
        Log.d(TAG, "changeData. movies = $movies ")
        val newMovie = movies[index]
        val scoreMap = mapOf(source!! to value!!)
        newMovie.score = scoreMap
        movies.toMutableList()[index] = newMovie
        callback(movies)
    }

    //    fun searchMovie(searchText: String,
//                    year: String, type: String?,
//                    callback: (List<RemoteMovie>) -> Unit,
//                    errorCallback: (e: Throwable) -> Unit,
//                    emptyListCallback: (Boolean) -> Unit){
//        Thread{
//            try {
//                val response = Network.getSearchMovieCall(searchText, year, type).execute()
//                val responseString = response.body()?.string().orEmpty()
//                val movieList = parseMovieResponse(responseString)
//                callback(movieList)
//                errorCallback(Throwable("network"))
//                if (responseString.contains("Movie not found!")){
//                    emptyListCallback(true)
//                } else {
//                    emptyListCallback(false)
//                }
//
//            } catch (e: IOException) {
//                Log.d("MyServer", "execute request error = ${e.message}", e)
//                callback(emptyList())
//                errorCallback(Throwable("no network"))
//            }
//            Thread.sleep(1000)
//        }.start()
//
//    }
}