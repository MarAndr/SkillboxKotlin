package com.example.skillboxkotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: Fragment(R.layout.fragment_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
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
        tv_fragmentDetail.text = requireArguments().getString(TEXT_KEY)


    }


    companion object{
        private const val TEXT_KEY = "text_key"

        fun newFragmentInstance(text: String): DetailFragment{
            return DetailFragment().withArguments {
                putString(TEXT_KEY, text)
            }
        }
    }
}