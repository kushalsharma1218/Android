package com.example.hijeck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText nametext,passtext;

    DatabaseHelper db;
    TextView alredyLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nametext=(EditText) findViewById(R.id.name);
        passtext=(EditText) findViewById(R.id.password);

        db=new DatabaseHelper(getApplicationContext());
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nametext.getText().toString();
                String pass = passtext.getText().toString();
                boolean namecheck = db.checkName(name);
                if (!namecheck) {
                    boolean checkrmailpass = db.emailpasscheck(name, pass);
                    if (checkrmailpass) {
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid details", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    {
                        Toast.makeText(getApplicationContext(), "We could not find any account with this name", Toast.LENGTH_SHORT).show();

                    }
            }
        });
        alredyLogin=(TextView)findViewById(R.id.createid);
        alredyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivtiy.class));
            }
        });
    }
}
