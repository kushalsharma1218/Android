package com.example.googlesummerofcode

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivtiy : AppCompatActivity() {
    //Database
    var db: DatabaseHelper? = null
    var b1: Button? = null
    var nametext: EditText? = null
    var passtext: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_activtiy)
        nametext = findViewById<View>(R.id.name) as EditText
        passtext = findViewById<View>(R.id.password) as EditText
        db = DatabaseHelper(applicationContext)
        b1 = findViewById<View>(R.id.signup) as Button
        b1!!.setOnClickListener {
            val name = nametext!!.text.toString()
            val pass = passtext!!.text.toString()
            if (name == "" || pass == "") {
                Toast.makeText(
                    applicationContext,
                    "Feilds cannot be Empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val checkName = db!!.checkName(name)
                if (checkName!!) {
                    val insert = db!!.insert(name, pass)
                    if (insert) {
                        startActivity(Intent(this@SignUpActivtiy, LoginActivity::class.java))
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Name already exits",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}