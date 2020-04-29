package com.example.skillboxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stringWithPoem = getString(R.string.spongeBobSong)
        val listWithPoemWords = stringWithPoem.split(" ")

        toolbarMainAct.setNavigationOnClickListener {
            toast("You have clicked on the Navigation Icon")
        }

        toolbarMainAct.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.firstElementMainMenu -> {
                    toast("You have clicked on item 1")
                    true
                }

                R.id.secondElementMainMenu -> {
                    toast("You have clicked on item 2")
                    true
                }

                else -> {
                    toast("You have clicked on item 3")
                    true
                }
            }
        }

        val searchItem = toolbarMainAct.menu.findItem(R.id.searchMainMenu)
        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listWithPoemWords.filter { it.contains(newText ?: "") }
                    .joinToString()
                    .let { tvSecondAct.text = it }
                return true
            }
        })

    }

    fun toast(toastMessage: String) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

}


