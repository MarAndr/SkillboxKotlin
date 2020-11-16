package module22

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class RemoteMovie(
    @Json(name = "imdbID")
    val id: String,
    @Json(name = "Title")
    var title: String,
    @Json(name = "Year")
    val year: String,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Poster")
    val poster: String,
    val rating: MovieRating = MovieRating.GENERAL,
    var score: Map<String,String> = emptyMap()
)