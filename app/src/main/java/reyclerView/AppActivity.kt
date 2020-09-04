package reyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skillboxkotlin.R

class AppActivity : AppCompatActivity(R.layout.activity_appactivity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            createListFragment()
        }

    }

    private fun createListFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer_appActivity, MovieFiguresListFragment())
            .commit()
    }
}