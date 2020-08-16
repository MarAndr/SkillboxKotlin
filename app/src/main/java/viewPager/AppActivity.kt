package viewPager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.skillboxkotlin.R

class AppActivity : AppCompatActivity(R.layout.activity_appactivity) {

    val KEY_FRAGMENT = "key_fragment"
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentFragment = if (savedInstanceState == null) {
            ViewPagerFragment()
        } else {
            supportFragmentManager.getFragment(savedInstanceState, KEY_FRAGMENT)!!
        }

        if (savedInstanceState == null){
            makeViewPagerFragment()
        }
    }

    fun makeViewPagerFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer_appActivity, currentFragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, KEY_FRAGMENT, currentFragment)
    }

}