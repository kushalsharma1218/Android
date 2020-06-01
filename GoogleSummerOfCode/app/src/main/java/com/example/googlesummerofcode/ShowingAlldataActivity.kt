package com.example.googlesummerofcode

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class ShowingAlldataActivity : AppCompatActivity() {
    var db: DataBaseClassAdding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showing_alldata)
        val listView =
            findViewById<View>(R.id.ListView) as ListView
        db = DataBaseClassAdding(this)
        val data = db!!.allData
        val adapter = UserSecretAdapter(this, R.layout.adapterview)
        if (data!!.count == 0) {
            Toast.makeText(
                applicationContext,
                "YOU HAVE NO SECRETS PLEASE ADD",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            while (data!!.moveToNext()) {
                val us =
                    UserSecrets(data!!.getString(0), data!!.getString(1), data!!.getString(2))
                adapter.add(us)
            }
            listView.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.searchmaenu, menu)
        menuInflater.inflate(R.menu.mymenu, menu)
        val searchitem = menu.findItem(R.id.search_menu)
        val searchView =
            searchitem.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.home -> {
            }
            R.id.logout -> {
                Toast.makeText(applicationContext, "LOGOUT", Toast.LENGTH_SHORT).show()
                db!!.cleardata()
                Toast.makeText(
                    applicationContext,
                    "You do not have any secrets",
                    Toast.LENGTH_SHORT
                ).show()
            }
            R.id.delete -> {
                db!!.cleardata()
                Toast.makeText(
                    applicationContext,
                    "You do not have any secrets",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }
}