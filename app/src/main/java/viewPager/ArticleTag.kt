package viewPager


enum class ArticleTag(val tag: String) {
    CHAMPION("Чемпион"),
    CHAMPION_LEAGUE ("Места в Лиге Чемпионов"),
    EUROPA_LEAGUE("Места в Лиге Европы");

    companion object {
        fun getArrayTags() = values().map { it.tag }.toTypedArray()
    }
}

