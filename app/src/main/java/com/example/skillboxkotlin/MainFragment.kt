package com.example.skillboxkotlin

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainFragment: Fragment(R.layout.fragment_main), ItemSelectListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d("MainFragment", "Landscape")
        }


        childFragmentManager.beginTransaction()
            .replace(R.id.container_mainFragment, ListFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()

    }

    override fun onItemSelect(text: String) {
        Log.d("MainFragment", "onTextClick=$text")
        childFragmentManager.beginTransaction()
            .replace(R.id.container_mainFragment, DetailFragment.newFragmentInstance(text))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}