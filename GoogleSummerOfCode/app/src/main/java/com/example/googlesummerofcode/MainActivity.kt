package com.example.googlesummerofcode

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val homeIntent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, MainActivity.Companion.SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private const val SPLASH_TIME_OUT = 4000
    }
}