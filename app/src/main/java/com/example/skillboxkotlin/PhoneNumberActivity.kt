package com.example.skillboxkotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_phone_number_activity.*
import java.util.regex.Pattern


class PhoneNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number_activity)



        btn_call.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()

            val isPhoneValid = Pattern.compile(
                "(\\+?\\d{11})"
            ) .matcher(phoneNumber).matches()
            if (isPhoneValid) {
                dialPhoneNumber(phoneNumber)
            } else {
                toast("Your enter wrong format of number. Try again")
            }

        }

        btn_toSite.setOnClickListener {
            val intentToSite = Intent(Intent.ACTION_VIEW, Uri.parse("http://student_name.com/info"))
            startActivity(intentToSite)
        }
    }


    fun dialPhoneNumber(phoneNumber: String) {
        val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (phoneIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(phoneIntent, PHONE_KEY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHONE_KEY) {
            if (resultCode != null) {
                toast("Your call has happened!")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    companion object {
        private const val PHONE_KEY = 123
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}