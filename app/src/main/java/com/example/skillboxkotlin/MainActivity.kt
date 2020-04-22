package com.example.skillboxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Glide.with(this).load("https://i.picsum.photos/id/103/2592/1936.jpg").into(imageView)

        loginButton.setOnClickListener {


            loginButton.isEnabled = false
            etEmail.isEnabled = false
            etPassword.isEnabled = false
            checkBox.isEnabled = false
            val progressBar = ProgressBar(this)
            container.addView(progressBar)
            Handler().postDelayed({
                loginButton.isEnabled = true
                etEmail.isEnabled = true
                etPassword.isEnabled = true
                checkBox.isEnabled = false
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Login was successful", Toast.LENGTH_SHORT).show()
            }, 2000)


        }
    }
}

