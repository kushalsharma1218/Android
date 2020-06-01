package com.example.hijeck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivtiy extends AppCompatActivity {


    //Database
    DatabaseHelper db;

    Button b1;
    EditText nametext,passtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activtiy);

        nametext=(EditText)findViewById(R.id.name);
        passtext=(EditText)findViewById(R.id.password);

        db = new DatabaseHelper(getApplicationContext());
        b1=(Button)findViewById(R.id.signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nametext.getText().toString();
                String pass=passtext.getText().toString();

                if(name.equals("") || pass.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Feilds cannot be Empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkName=db.checkName(name);
                    if(checkName)
                    {
                        boolean insert = db.insert(name,pass);
                        if(insert)
                        {
                            startActivity(new Intent(SignUpActivtiy.this,LoginActivity.class));
                        }
                    }
                    else
                        {
                            Toast.makeText(getApplicationContext(),"Name already exits",Toast.LENGTH_SHORT).show();
                        }

                }

            }
        });
    }
}
