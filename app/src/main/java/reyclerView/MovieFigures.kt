package reyclerView

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.io.Serializable

sealed class MovieFigures: Parcelable {

    data class Actor(
        val name: String,
        val age: Int,
        val avatarLink: String,
        val isGetOscar: Boolean
    ): MovieFigures(), Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString().orEmpty(),
            parcel.readInt(),
            parcel.readString().orEmpty(),
            parcel.readByte() != 0.toByte()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(age)
            parcel.writeString(avatarLink)
            parcel.writeByte(if (isGetOscar) 1 else 0)
        }

        override fun describeContents(): Int {
            TODO("Not yet implemented")
        }

        companion object CREATOR : Parcelable.Creator<Actor> {
            override fun createFromParcel(parcel: Parcel): Actor {
                return Actor(parcel)
            }

            override fun newArray(size: Int): Array<Actor?> {
                return arrayOfNulls(size)
            }
        }
    }


    data class FilmDirector(
        val name: String,
        val age: Int,
        val avatarLink: String,
        val genres: String,
        val isGetOscar: Boolean
    ) : MovieFigures(), Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString().orEmpty(),
            parcel.readInt(),
            parcel.readString().orEmpty(),
            parcel.readString().orEmpty(),
            parcel.readByte() != 0.toByte()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(age)
            parcel.writeString(avatarLink)
            parcel.writeString(genres)
            parcel.writeByte(if (isGetOscar) 1 else 0)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<FilmDirector> {
            override fun createFromParcel(parcel: Parcel): FilmDirector {
                return FilmDirector(parcel)
            }

            override fun newArray(size: Int): Array<FilmDirector?> {
                return arrayOfNulls(size)
            }
        }
    }
}