package com.example.googlesummerofcode

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    var nametext: EditText? = null
    var passtext: EditText? = null
    var db: DatabaseHelper? = null
    var alredyLogin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        nametext = findViewById<View>(R.id.name) as EditText
        passtext = findViewById<View>(R.id.password) as EditText
        db = DatabaseHelper(applicationContext)
        val login =
            findViewById<View>(R.id.login) as Button
        login.setOnClickListener {
            val name = nametext!!.text.toString()
            val pass = passtext!!.text.toString()
            val namecheck = db!!.checkName(name)
            if (!namecheck) {
                val checkrmailpass = db!!.emailpasscheck(name, pass)
                if (checkrmailpass) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Invalid details",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "We could not find any account with this name",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        alredyLogin = findViewById<View>(R.id.createid) as TextView
        alredyLogin!!.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    SignUpActivtiy::class.java
                )
            )
        }
    }
}