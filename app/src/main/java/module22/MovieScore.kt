package module22

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieScore(
    var source: String,
    var value: String
)