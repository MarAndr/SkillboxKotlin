package com.example.module2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView : TextView = findViewById(R.id.tvMain)
        textView.text = """
            BuildType = ${BuildConfig.BUILD_TYPE}
            Flavor = ${BuildConfig.FLAVOR}
            VersionCode = ${BuildConfig.VERSION_CODE}
            VersionName = ${BuildConfig.VERSION_NAME}
            ApplicationId = ${BuildConfig.APPLICATION_ID}
        """.trimIndent()
    }
}
