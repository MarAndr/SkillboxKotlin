package reyclerView

sealed class MovieFigures {
    data class Actor(
        val name: String,
        val age: Int,
        val avatarLink: String,
        val isGetOscar: Boolean
    ): MovieFigures()

    data class FilmDirector(
        val name: String,
        val age: Int,
        val avatarLink: String,
        val genres: String,
        val isGetOscar: Boolean
    ): MovieFigures()
}