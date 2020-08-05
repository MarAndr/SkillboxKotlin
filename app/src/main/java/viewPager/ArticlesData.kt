package viewPager

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArticlesData(
    @StringRes val textRes: Int,
    @DrawableRes val imageRes: Int,
    val tags: List<ArticleTag>,
    val header: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        TODO("tags"),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(textRes)
        parcel.writeInt(imageRes)
        parcel.writeString(header)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticlesData> {
        override fun createFromParcel(parcel: Parcel): ArticlesData {
            return ArticlesData(parcel)
        }

        override fun newArray(size: Int): Array<ArticlesData?> {
            return arrayOfNulls(size)
        }
    }
}