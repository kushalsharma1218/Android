package com.example.googlesummerofcode

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class UpdateActivity : AppCompatActivity() {
    var db: DataBaseClassAdding? = null
    var passupdate: String? = null
    var appname: EditText? = null
    var username: EditText? = null
    var builder: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialouge_view, null)
        builder = AlertDialog.Builder(this).create()
        db = DataBaseClassAdding(this)
        appname = findViewById<View>(R.id.application_name) as EditText
        username = findViewById<View>(R.id.user_name) as EditText
        val passwordtext =
            dialogView.findViewById<View>(R.id.password_dialouge) as EditText
        builder!!.setIcon(R.drawable.logo)
        val update =
            dialogView.findViewById<View>(R.id.update_btn) as Button
        val cancel =
            dialogView.findViewById<View>(R.id.cancle_btn) as Button
        val Search =
            findViewById<View>(R.id.search_btn) as Button
        Search.setOnClickListener {
            val emailfield = db!!.checkUserNameWithFireld(
                appname!!.text.toString(),
                username!!.text.toString()
            )
            if (!emailfield) {
                update.setOnClickListener {
                    if (passwordtext.text.toString().isEmpty()) {
                        Toast.makeText(
                            applicationContext,
                            "Empty Field",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val updateBool = db!!.update(
                            appname!!.text.toString(),
                            username!!.text.toString(),
                            passwordtext.text.toString()
                        )
                        if (updateBool) {
                            builder!!.dismiss()
                            Toast.makeText(
                                applicationContext,
                                "Data Updated Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                cancel.setOnClickListener { builder!!.dismiss() }
                builder!!.setView(dialogView)
                builder!!.show()
            } else {
                Toast.makeText(applicationContext, "No secrets found", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        builder!!.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        builder!!.dismiss()
    }
}