package com.example.skillboxkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_deeplink.*

class DeepLink : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deeplink)
        handleIntentData()
    }

    private fun handleIntentData() {
        intent.data?.host.let { resourceName ->
            tv_DeepLinkAct.text = resourceName
        }
    }
}