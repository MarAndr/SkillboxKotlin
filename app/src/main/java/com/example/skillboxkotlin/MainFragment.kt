package com.example.skillboxkotlin

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainFragment: Fragment(R.layout.fragment_main), ItemSelectListener {

    private lateinit var currentChildFragment: Fragment
    private val KEY_CHILD_FRAGMENT = "key_child_fragment"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        currentChildFragment = if (savedInstanceState == null){
            ListFragment()
        } else {
            childFragmentManager.getFragment(savedInstanceState, KEY_CHILD_FRAGMENT)!!
        }

        val transaction = childFragmentManager.beginTransaction()

        if (resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE){
                transaction
                    .replace(R.id.container_mainFragment, currentChildFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
        } else {
            childFragmentManager.findFragmentById(R.id.container_mainFragment)?.let {
                childFragmentManager.beginTransaction()
                    .remove(it)
                    .commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        childFragmentManager.putFragment(outState, KEY_CHILD_FRAGMENT, currentChildFragment)
    }


    override fun onItemSelect(text: String) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_mainFragment, DetailFragment.newFragmentInstance(text))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }
}