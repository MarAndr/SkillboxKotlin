package com.example.skillboxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), OpenMainFragment {

    private val FRAGMENT_SAVING_KEY = "fragment key"
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentFragment = if (savedInstanceState == null) {
            LoginFragment()
        } else {
            supportFragmentManager.getFragment(savedInstanceState, FRAGMENT_SAVING_KEY)!!
        }

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.container_mainAct, currentFragment)
            transaction.commit()
        }

    }

    override fun onBackPressed() {
        val mainFragment = supportFragmentManager.findFragmentByTag("main_fragment") as Fragment
        if (mainFragment.childFragmentManager.backStackEntryCount > 0) {
            mainFragment.childFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentFragment =
            supportFragmentManager.getFragment(savedInstanceState, FRAGMENT_SAVING_KEY)!!
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, FRAGMENT_SAVING_KEY, currentFragment)
    }

    override fun openMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        currentFragment = MainFragment()
        transaction.replace(R.id.container_mainAct, currentFragment, "main_fragment")
        transaction.commit()

    }
}

