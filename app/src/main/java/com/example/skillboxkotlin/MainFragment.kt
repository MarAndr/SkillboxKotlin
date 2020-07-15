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

class MainFragment : Fragment(R.layout.fragment_main), ItemSelectListener {

    private var currentChildFragment: Fragment? = null
    private val KEY_CHILD_FRAGMENT = "key_child_fragment"
    private val KEY_LIST_FRAGMENT = "key_list_fragment"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            childFragmentManager.getFragment(savedInstanceState, KEY_CHILD_FRAGMENT)?.let {
                currentChildFragment = it
            }
        }

        val transaction = childFragmentManager.beginTransaction()

        if (resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE) {
            currentChildFragment = null
            transaction
                .replace(R.id.container_mainFragment, ListFragment(), KEY_LIST_FRAGMENT)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        } else {

            childFragmentManager.popBackStack()
            childFragmentManager.findFragmentByTag(KEY_LIST_FRAGMENT)?.let {
                childFragmentManager.beginTransaction()
                    .remove(it)
                    .commit()
            }
            currentChildFragment?.let {
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.container_mainFragment, it)
                    .commit()
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        childFragmentManager.findFragmentById(R.id.container_mainFragment)?.let {
            childFragmentManager.putFragment(outState, KEY_CHILD_FRAGMENT, it)
        }
    }


    override fun onItemSelect(text: String) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_mainFragment, DetailFragment.newFragmentInstance(text))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }
}