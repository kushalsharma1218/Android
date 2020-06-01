package com.example.googlesummerofcode

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    var builder: AlertDialog.Builder? = null
    var db: DataBaseClassAdding? = null
    var applicationnametext: EditText? = null
    var usernametext: EditText? = null
    var passtext: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        builder = AlertDialog.Builder(this)
        db = DataBaseClassAdding(this)
        applicationnametext = findViewById<View>(R.id.application_name) as EditText
        usernametext = findViewById<View>(R.id.user_name) as EditText
        passtext = findViewById<View>(R.id.password) as EditText
        val add_details =
            findViewById<View>(R.id.add_details) as Button
        add_details.setOnClickListener {
            val application_name = applicationnametext!!.text.toString()
            val username = usernametext!!.text.toString()
            val pass = passtext!!.text.toString()
            if (application_name.isEmpty() || username.isEmpty() || pass.isEmpty()) {
                Toast.makeText(applicationContext, "Fields are Empty ", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val checkUSerNAmeWithApp =
                    db!!.checkUserNameWithFireld(application_name, username)
                if (checkUSerNAmeWithApp) {
                    val ins = db!!.insertData(application_name, username, pass)
                    if (ins) {
                        applicationnametext!!.setText("")
                        passtext!!.setText("")
                        usernametext!!.setText("")
                        Toast.makeText(
                            applicationContext,
                            "Details Secured",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "You have already added details for $application_name with this credentials",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.home -> {
            }
            R.id.displayall -> startActivity(
                Intent(
                    applicationContext,
                    ShowingAlldataActivity::class.java
                )
            )
            R.id.logout -> Toast.makeText(
                applicationContext,
                "LOGOUT",
                Toast.LENGTH_SHORT
            ).show()
            R.id.update -> startActivity(Intent(this@HomeActivity, UpdateActivity::class.java))
            R.id.delete -> {
                builder!!.setMessage("Do you want to close this application ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        db!!.cleardata()
                        Toast.makeText(
                            applicationContext,
                            "All secrets are deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        //  Action for 'NO' Button
                        dialog.cancel()
                    }
                val alert = builder!!.create()
                alert.setTitle("Deleting All Secrets")
                alert.show()
            }
        }
        return true
    }
}