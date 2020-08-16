package viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class Adapter(fragment: ViewPagerFragment,
val screens: List<ArticlesData>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return screens.size
    }

    override fun createFragment(position: Int): Fragment {
        val screen = screens[position]
        return ArticleFragment.newInstance(
            screen.textRes,
            screen.imageRes
        )
    }
}