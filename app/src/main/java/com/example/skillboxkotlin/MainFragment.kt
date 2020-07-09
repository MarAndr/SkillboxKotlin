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

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val transaction = childFragmentManager.beginTransaction()
        val listFragment = ListFragment()

        if (resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE){
                transaction
                    .add(R.id.container_mainFragment, listFragment)
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

    override fun onItemSelect(text: String) {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_mainFragment, DetailFragment.newFragmentInstance(text))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }
}