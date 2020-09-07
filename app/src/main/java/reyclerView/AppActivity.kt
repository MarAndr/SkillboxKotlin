package reyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skillboxkotlin.R
import reyclerView.extensions.StartButtonClick
import reyclerView.fragments.MainFragment
import reyclerView.fragments.MovieGridListFragment
import reyclerView.fragments.MovieLinearListFragment
import reyclerView.fragments.MovieStaggeredListFragment

class AppActivity : AppCompatActivity(com.example.skillboxkotlin.R.layout.activity_appactivity), StartButtonClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            createMainFragment()
        }

    }

    private fun createMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer_appActivity, MainFragment())
            .commit()
    }

    override fun onLinearButtonClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_appActivity, MovieLinearListFragment())
            .commit()
    }

    override fun onGridButtonClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_appActivity, MovieGridListFragment())
            .commit()
    }

    override fun onStaggeredButtonClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_appActivity, MovieStaggeredListFragment())
            .commit()
    }
}