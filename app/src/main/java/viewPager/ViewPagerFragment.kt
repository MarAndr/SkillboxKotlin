package viewPager

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.skillboxkotlin.R
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_viewpager.*
import kotlin.random.Random

class ViewPagerFragment : Fragment(R.layout.fragment_viewpager), MakeBadge, OnTagsChoose {

    private var screens: List<ArticlesData> = listOf()
    private var filteredArticles: MutableList<ArticlesData> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar_viewPagerFragment.setOnMenuItemClickListener { menuItem: MenuItem? ->
            when (menuItem?.itemId) {
                R.id.filterItem_menuViewPager -> {
                    FilterDialogFragment().show(childFragmentManager, "dialogFragment")
                    true
                }
                else -> {
                    false
                }
            }
        }

        screens = listOf(
            ArticlesData(
                textRes = R.string.ZenitInfo,
                imageRes = R.drawable.zenit_logo,
                tags = listOf(
                    ArticleTag.CHAMPION,
                    ArticleTag.CHAMPION_LEAGUE
                ),
                header = "Зенит"

            ), ArticlesData(
                textRes = R.string.LokoInfo,
                imageRes = R.drawable.loko_logo,
                tags = listOf(
                    ArticleTag.CHAMPION_LEAGUE
                ),
                header = "Локомотив"
            ), ArticlesData(
                textRes = R.string.KrasnodarInfo,
                imageRes = R.drawable.krasnodar_logo,
                tags = listOf(
                    ArticleTag.CHAMPION_LEAGUE
                ),
                header = "Краснодар"
            ), ArticlesData(
                textRes = R.string.CSKAInfo,
                imageRes = R.drawable.cska_logo,
                tags = listOf(
                    ArticleTag.EUROPA_LEAGUE
                ),
                header = "ЦСКА"
            ), ArticlesData(
                textRes = R.string.RostovInfo,
                imageRes = R.drawable.rostov_logo,
                tags = listOf(
                    ArticleTag.EUROPA_LEAGUE
                ),
                header = "Ростов"
            )
        )

        if (savedInstanceState == null) {
            makeViewPager(screens)
        } else {
            makeViewPager(filteredArticles)
        }


    }

    override fun makeBadge() {
        tabLayout.getTabAt(Random.nextInt(0, tabLayout.tabCount))?.orCreateBadge?.apply {
            number += 1
            badgeGravity = BadgeDrawable.TOP_END
        }
    }

    override fun choosedTag(listOfTags: ArrayList<String>) {
        filteredArticles.clear()
        listOfTags.forEach { choosedTagString ->
            for (param in screens) {
                param.tags.forEach { articleTag ->
                    if (articleTag.name == choosedTagString) {
                        if (!filteredArticles.contains(param)) {
                            filteredArticles.add(param)
                        }

                    }
                }
            }
        }
        makeViewPager(filteredArticles)
    }

    fun makeViewPager(articles: List<ArticlesData>) {
        val adapter = Adapter(this, articles)
        viewPager_viewPagerFragment.adapter = adapter
        spring_dots_indicator.setViewPager2(viewPager_viewPagerFragment)
        viewPager_viewPagerFragment.setPageTransformer(object : ViewPager2.PageTransformer {


            override fun transformPage(page: View, position: Float) {
                page.setCameraDistance(20000f);


                if (position < -1) {     // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.setAlpha(0f);

                } else if (position <= 0) {    // [-1,0]
                    page.setAlpha(1f);
                    page.setPivotX(page.getWidth().toFloat());
                    page.setRotationY(90 * Math.abs(position));

                } else if (position <= 1) {    // (0,1]
                    page.setAlpha(1f);
                    page.setPivotX(0f);
                    page.setRotationY(-90 * Math.abs(position));

                } else {    // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.setAlpha(0f);

                }
            }
        })

        TabLayoutMediator(tabLayout, viewPager_viewPagerFragment) { tab, position ->
            when (position) {
                0 -> tab.text = when (articles[position].imageRes) {
                    R.drawable.zenit_logo -> "Зенит"
                    R.drawable.loko_logo -> "Локомотив"
                    R.drawable.krasnodar_logo -> "Краснодар"
                    R.drawable.cska_logo -> "ЦСКА"
                    else -> "Ростов"
                }
                1 -> tab.text = when (articles[position].imageRes) {
                    R.drawable.zenit_logo -> "Зенит"
                    R.drawable.loko_logo -> "Локомотив"
                    R.drawable.krasnodar_logo -> "Краснодар"
                    R.drawable.cska_logo -> "ЦСКА"
                    else -> "Ростов"
                }
                2 -> tab.text = when (articles[position].imageRes) {
                    R.drawable.zenit_logo -> "Зенит"
                    R.drawable.loko_logo -> "Локомотив"
                    R.drawable.krasnodar_logo -> "Краснодар"
                    R.drawable.cska_logo -> "ЦСКА"
                    else -> "Ростов"
                }
                3 -> tab.text = when (articles[position].imageRes) {
                    R.drawable.zenit_logo -> "Зенит"
                    R.drawable.loko_logo -> "Локомотив"
                    R.drawable.krasnodar_logo -> "Краснодар"
                    R.drawable.cska_logo -> "ЦСКА"
                    else -> "Ростов"
                }
                4 -> tab.text = when (articles[position].imageRes) {
                    R.drawable.zenit_logo -> "Зенит"
                    R.drawable.loko_logo -> "Локомотив"
                    R.drawable.krasnodar_logo -> "Краснодар"
                    R.drawable.cska_logo -> "ЦСКА"
                    else -> "Ростов"
                }
            }
        }.attach()





        viewPager_viewPagerFragment.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })
    }

}


