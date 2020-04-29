package com.example.skillboxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load("https://i.picsum.photos/id/1026/4621/3070.jpg").into(imageView)

        loginButton.setOnClickListener {

            loginButton.isEnabled = false
            etEmail.isEnabled = false
            etPassword.isEnabled = false
            checkBox.isEnabled = false

            val layout: ConstraintLayout = findViewById(R.id.container)
            val set = ConstraintSet()
            set.clone(layout)
            val progressBar = ProgressBar(this)
            progressBar.id = View.generateViewId()
            layout.addView(progressBar)
            set.connect(
                progressBar.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM,
                150
            )
            set.connect(
                progressBar.id,
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                0
            )
            set.connect(
                progressBar.id,
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                0
            )
            set.constrainHeight(progressBar.id, 150)
            set.applyTo(layout)

            Handler().postDelayed({
                loginButton.isEnabled = true
                etEmail.isEnabled = true
                etPassword.isEnabled = true
                checkBox.isEnabled = true
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Login was successful", Toast.LENGTH_SHORT).show()
            }, 2000)

        }

    }
}

