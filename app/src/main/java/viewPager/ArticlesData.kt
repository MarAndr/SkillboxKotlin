package viewPager

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ArticlesData(
    @StringRes val textRes: Int,
    @DrawableRes val imageRes: Int,
    val tags: Set<ArticleTag>,
    val header: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        TODO("tags"),
        parcel.readString()
    ) {
    }

    companion object : Parceler<ArticlesData> {

        override fun ArticlesData.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(textRes)
            parcel.writeInt(imageRes)
            parcel.writeString(header)
        }

        override fun create(parcel: Parcel): ArticlesData {
            return ArticlesData(parcel)
        }
    }
}