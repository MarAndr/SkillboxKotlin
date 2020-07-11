package com.example.skillboxkotlin

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.skillboxkotlin.LoginFragment.Companion.KEY_FORM_STATE
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private var state: FormState = FormState(true, "")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable<FormState>(KEY_FORM_STATE)
                ?: error("Unexpected state")
        }

        loginButton.setOnClickListener {

            val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()


            if (isEmailValid && etPassword.text.length > 6 && checkBox.isChecked) {
                state = state.noError()
                (activity as OpenMainFragment).openMainFragment()

            } else {
                state = state.withError()
                textView_error.isVisible = true
                textView_error.text = state.message
                Handler().postDelayed({
                    textView_error.isVisible = false
                }, 5000)
            }


        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_FORM_STATE, state)
    }

    companion object {
        private const val KEY_FORM_STATE = "formState"
    }


}