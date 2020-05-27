package com.example.skillboxkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tag = "Main Activity"
    private var state: FormState = FormState(true, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable<FormState>(KEY_FORM_STATE)
                ?: error("Unexpected state")
        }


        Log.v(tag, "onCreated was called")
        Log.d(tag, "onCreated was called")
        Log.i(tag, "onCreated was called")
        Log.e(tag, "onCreated was called")


        Glide.with(this).load("https://i.picsum.photos/id/1026/4621/3070.jpg").into(imageView)

        loginButton.setOnClickListener {

            val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()

            if (isEmailValid && etPassword.text.length > 6 && checkBox.isChecked) {
                state = state.noError()

                val intentToPhoneNumberActivity = Intent(this, PhoneNumberActivity::class.java)
                startActivity(intentToPhoneNumberActivity)

            } else {
                state = state.withError()
                textView_error.isVisible = true
                textView_error.text = state.message
                Handler().postDelayed({
                    textView_error.isVisible = false
                }, 5000)
            }


        }



        buttonANR.setOnClickListener {
            Thread.sleep(10000)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.v(tag, "onStart was called")
        Log.d(tag, "onStart was called")
        Log.i(tag, "onStart was called")
        Log.e(tag, "onStart was called")
    }

    override fun onResume() {
        super.onResume()
        Log.v(tag, "onResume was called")
        Log.d(tag, "onResume was called")
        Log.i(tag, "onResume was called")
        Log.e(tag, "onResume was called")
    }

    override fun onPause() {
        super.onPause()
        Log.v(tag, "onPause was called")
        Log.d(tag, "onPause was called")
        Log.i(tag, "onPause was called")
        Log.e(tag, "onPause was called")
    }

    override fun onStop() {
        super.onStop()
        finish()
        Log.v(tag, "onStop was called")
        Log.d(tag, "onStop was called")
        Log.i(tag, "onStop was called")
        Log.e(tag, "onStop was called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "onDestroy was called")
        Log.d(tag, "onDestroy was called")
        Log.i(tag, "onDestroy was called")
        Log.e(tag, "onDestroy was called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_FORM_STATE, state)
    }

    companion object {
        private const val KEY_FORM_STATE = "formState"
    }

}


