package module16.data

data class Data(
    val id: Long,
    var currentTime: org.threeten.bp.Instant,
    val lat: Double,
    val lng: Double,
    val speed: Float,
    val accuracy: Float,
    val imageLink: String
)

