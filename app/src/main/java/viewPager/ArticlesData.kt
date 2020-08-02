package viewPager

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArticlesData(
    @StringRes val textRes: Int,
    @DrawableRes val imageRes: Int,
    val tags: List<ArticleTag>,
    val header: String
)