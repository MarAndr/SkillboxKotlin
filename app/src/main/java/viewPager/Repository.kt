package viewPager

import com.example.skillboxkotlin.R

class Repository {
    fun getArticles(): List<ArticlesData> {
        return listOf(
            ArticlesData(
                textRes = R.string.ZenitInfo,
                imageRes = R.drawable.zenit_logo,
                tags = setOf(
                    ArticleTag.CHAMPION,
                    ArticleTag.CHAMPION_LEAGUE
                ),
                header = "Зенит"

            ), ArticlesData(
                textRes = R.string.LokoInfo,
                imageRes = R.drawable.loko_logo,
                tags = setOf(
                    ArticleTag.CHAMPION_LEAGUE
                ),
                header = "Локомотив"
            ), ArticlesData(
                textRes = R.string.KrasnodarInfo,
                imageRes = R.drawable.krasnodar_logo,
                tags = setOf(
                    ArticleTag.CHAMPION_LEAGUE
                ),
                header = "Краснодар"
            ), ArticlesData(
                textRes = R.string.CSKAInfo,
                imageRes = R.drawable.cska_logo,
                tags = setOf(
                    ArticleTag.EUROPA_LEAGUE
                ),
                header = "ЦСКА"
            ), ArticlesData(
                textRes = R.string.RostovInfo,
                imageRes = R.drawable.rostov_logo,
                tags = setOf(
                    ArticleTag.EUROPA_LEAGUE
                ),
                header = "Ростов"
            )
        )
    }
}