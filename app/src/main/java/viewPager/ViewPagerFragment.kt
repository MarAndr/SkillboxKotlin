package viewPager

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.skillboxkotlin.R
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_viewpager.*
import viewPager.FilterDialogFragment.Companion.CHOOSED_TAGS_KEY
import kotlin.random.Random

class ViewPagerFragment : Fragment(R.layout.fragment_viewpager), MakeBadge, DialogResultListener {

    private lateinit var articles: List<ArticlesData>
    private lateinit var filteredArticles: List<ArticlesData>
    private val KEY_DATA = "key_data"
    private val tagsState = BooleanArray(ArticleTag.getArrayTags().size) { true }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        articles = Repository().getArticles()

        if (savedInstanceState != null) {
            val tagsStateArray = savedInstanceState.getBooleanArray(CHOOSED_TAGS_KEY)
            filteredArticles = savedInstanceState?.getParcelableArray(KEY_DATA)!!
                .toMutableList() as MutableList<ArticlesData>
            tagsStateArray?.copyInto(tagsState)
            updateData()
            makeViewPager()
        } else {
            updateData()
            makeViewPager()
        }

        setupToolbar()
    }

    fun setupToolbar() {
        toolbar_viewPagerFragment.setOnMenuItemClickListener { menuItem: MenuItem? ->
            when (menuItem?.itemId) {
                R.id.filterItem_menuViewPager -> {
                    createFilterDialog()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun createFilterDialog() {
        FilterDialogFragment.newInstance(tagsState.copyOf())
            .show(childFragmentManager, "dialogFragment")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_DATA, filteredArticles.toTypedArray())
        outState.putBooleanArray(CHOOSED_TAGS_KEY, tagsState)
    }

    override fun makeBadge() {
        tabLayout.getTabAt(Random.nextInt(0, tabLayout.tabCount))?.orCreateBadge?.apply {
            number += 1
            badgeGravity = BadgeDrawable.TOP_END
        }
    }

    fun makeViewPager() {
        val adapter = Adapter(this, filteredArticles)
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
            tab.text = filteredArticles[position].header

        }.attach()

        viewPager_viewPagerFragment.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })
    }

    override fun applyFilter(filter: BooleanArray) {
        filter.copyInto(tagsState)
        updateData()
        makeViewPager()
    }

    private fun updateData() {
        val filterSet = convertBooleanStateToTags()
        filteredArticles = getFilteredArticleByTag(filterSet)

    }

    private fun convertBooleanStateToTags(): Set<ArticleTag> {
        val filteredTags = mutableListOf<ArticleTag>()
        tagsState.forEachIndexed { index, isChecked ->
            if (isChecked) filteredTags.add(ArticleTag.values()[index])
        }
        return filteredTags.toSet()
    }

    private fun getFilteredArticleByTag(filterSet: Set<ArticleTag>): List<ArticlesData> {
        return if (filterSet.size == ArticleTag.values().size) articles
        else articles.filter { article ->
            filterSet.any { tag ->
                tag in article.tags
            }
        }
    }

}


