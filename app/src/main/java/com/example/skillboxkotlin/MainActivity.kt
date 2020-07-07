package com.example.skillboxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    var loginFragment: Fragment = LoginFragment()
    private val LOGIN_FRAGMENT_KEY = "fragment key"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            if (savedInstanceState == null){
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(R.id.container_mainAct, LoginFragment())
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

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        loginFragment = supportFragmentManager.getFragment(savedInstanceState, LOGIN_FRAGMENT_KEY)!!
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        supportFragmentManager.putFragment(outState, LOGIN_FRAGMENT_KEY, loginFragment)
//    }
}

