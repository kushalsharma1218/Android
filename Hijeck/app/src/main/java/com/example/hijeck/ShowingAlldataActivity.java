package com.example.hijeck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowingAlldataActivity extends AppCompatActivity {

    DataBaseClassAdding db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_alldata);

        ListView listView = (ListView) findViewById(R.id.ListView);
        db = new DataBaseClassAdding(this);

        Cursor data = db.getAllData();
        UserSecretAdapter adapter = new UserSecretAdapter(this, R.layout.adapterview);

        if (data.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "YOU HAVE NO SECRETS PLEASE ADD", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {

                UserSecrets us = new UserSecrets(data.getString(0), data.getString(1), data.getString(2));
                adapter.add(us);
            }
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmaenu,menu);
        getMenuInflater().inflate(R.menu.mymenu, menu);
        MenuItem searchitem=menu.findItem(R.id.search_menu);

        SearchView searchView= (SearchView) searchitem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                break;
            case R.id.logout:
                Toast.makeText(getApplicationContext(), "LOGOUT", Toast.LENGTH_SHORT).show();
            case R.id.delete:
                db.cleardata();
                Toast.makeText(getApplicationContext(), "You do not have any secrets", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
