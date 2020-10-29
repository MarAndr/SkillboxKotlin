package module21

import android.util.Log
import android.widget.Toast
import com.example.mytestinglaboratory.my_training.network.movielist.RemoteMovie
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MovieRepository {

    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie>{
        return try {
            val jsonObject = JSONObject(responseBodyString)
            val movieArray = jsonObject.getJSONArray("Search")
            val movies = (0 until movieArray.length()).map {index -> movieArray.getJSONObject(index) }.map { movieJsonObject ->
                val title = movieJsonObject.getString("Title")
                val year = movieJsonObject.getString("Year")
                val id = movieJsonObject.getString("imdbID")
                val type = movieJsonObject.getString("Type")
                val poster = movieJsonObject.getString("Poster")
                RemoteMovie(id, title, year, type, poster)
            }
            movies
        } catch (e: JSONException) {
            Log.d("MyServer", "parse response error = ${e.message}", e)
            emptyList()
        }
    }

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
}