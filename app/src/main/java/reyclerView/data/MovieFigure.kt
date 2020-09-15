package reyclerView.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class MovieFigure: Parcelable {

    @Parcelize
    data class Actor(
        val id: Long,
        val name: String,
        val age: Int,
        val avatarLink: String,
        val isGetOscar: Boolean
    ) : MovieFigure(), Parcelable


    @Parcelize
    data class FilmDirector(
        val id: Long,
        val name: String,
        val age: Int,
        val avatarLink: String,
        val genres: String,
        val isGetOscar: Boolean
    ) : MovieFigure(), Parcelable
}

enum class MovieFigureEnum(val movieFigureName: String) {
    ACTOR("Actor"),
    FILM_DIRECTOR("Film director");
}