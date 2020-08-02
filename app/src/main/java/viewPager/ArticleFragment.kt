package viewPager

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.fragment_articlefragment.*

class ArticleFragment : Fragment(R.layout.fragment_articlefragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_articleFragment.setText(requireArguments().getInt(TEXT_KEY))
        iv_articleFragment.setImageResource(requireArguments().getInt(IMAGE_KEY))
        btnGenEvent_articleFragment.setOnClickListener {
            onButtonClick()
        }
    }

    fun onButtonClick() {
        (parentFragment as MakeBadge).makeBadge()
    }

    companion object {

        val tags: List<ArticleTag> = listOf(
            ArticleTag.CHAMPION,
            ArticleTag.CHAMPION_LEAGUE,
            ArticleTag.EUROPA_LEAGUE
        )

        const val TEXT_KEY = "text_key"
        const val IMAGE_KEY = "image_key"

        fun newInstance(
            @StringRes textRes: Int,
            @DrawableRes imageRes: Int
        ): ArticleFragment {
            return ArticleFragment().withArguments {
                putInt(TEXT_KEY, textRes)
                putInt(IMAGE_KEY, imageRes)
            }
        }
    }
}