package com.example.skillboxkotlin

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {


    private val itemSelectListener: ItemSelectListener?
        get() = parentFragment?.let { it as ItemSelectListener }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as? TextView }
            .forEach { textView ->
                textView.setOnClickListener {
                    onTextClick(textView.text.toString())
                }

            }
    }

    private fun onTextClick(textViewText: String) {
        Log.d("ListFragment", "onTextClick=$textViewText")
        when (textViewText) {
            "Зенит" -> itemSelectListener?.onItemSelect(resources.getString(R.string.ZenitInfo))
            "Локомотив" -> itemSelectListener?.onItemSelect(resources.getString(R.string.LokoInfo))
            "Краснодар" -> itemSelectListener?.onItemSelect(resources.getString(R.string.KrasnodarInfo))
            "Ростов" -> itemSelectListener?.onItemSelect(resources.getString(R.string.RostovInfo))
            else -> itemSelectListener?.onItemSelect(resources.getString(R.string.CSKAInfo))
        }

    }

}