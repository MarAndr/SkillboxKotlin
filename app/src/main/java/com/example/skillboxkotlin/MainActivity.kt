package com.example.skillboxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
            toast("You clicked on navigation icon")
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
                else -> false
            }
        }

        val searchItem = toolbarMainAct.menu.findItem(R.id.searchMainMenu)

        searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                toast("You expand the search")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                textView.text = stringWithPoem
                return true
            }

        })


        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listWithPoemWords.filter { it.contains(newText ?: "", true) }
                    .joinToString()
                    .let { textView.text = it }
                return true
            }
        })

    }

    fun toast(toastMessage: String) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }

}


