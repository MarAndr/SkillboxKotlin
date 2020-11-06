package module22

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search (
    @Json(name = "Search")
    val search: List<RemoteMovie>
)