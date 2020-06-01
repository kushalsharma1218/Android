package com.example.hijeck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    DataBaseClassAdding db;
    EditText applicationnametext,usernametext,passtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        builder= new AlertDialog.Builder(this);

        db=new DataBaseClassAdding(this);
        applicationnametext=(EditText)findViewById(R.id.application_name);
        usernametext=(EditText)findViewById(R.id.user_name);
        passtext=(EditText)findViewById(R.id.password);

        Button add_details=(Button)findViewById(R.id.add_details);
        add_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String application_name=applicationnametext.getText().toString();
                String username=usernametext.getText().toString();
                String pass=passtext.getText().toString();

                if(application_name.isEmpty() || username.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields are Empty ",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        boolean checkUSerNAmeWithApp=db.checkUserNameWithFireld(application_name,username);
                        if(checkUSerNAmeWithApp)
                        {
                            boolean ins=db.insertData(application_name,username,pass);
                            if(ins)
                            {
                                applicationnametext.setText("");
                                passtext.setText("");
                                usernametext.setText("");
                                Toast.makeText(getApplicationContext(),"Details Secured",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            {
                                Toast.makeText(getApplicationContext(),"You have already added details for "+application_name+" with this credentials",Toast.LENGTH_SHORT).show();

                            }
                    }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        switch (id)
        {
            case R.id.home:
                break;
            case R.id.displayall:
                startActivity(new Intent(getApplicationContext(),ShowingAlldataActivity.class));
                break;
            case R.id.logout:
                Toast.makeText(getApplicationContext(),"LOGOUT",Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                startActivity(new Intent(HomeActivity.this,UpdateActivity.class));
                break;
             case R.id.delete:
                 builder.setMessage("Do you want to close this application ?")
                         .setCancelable(false)
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 db.cleardata();
                                 Toast.makeText(getApplicationContext(),"All secrets are deleted",Toast.LENGTH_SHORT).show();
                             }
                         })
                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 //  Action for 'NO' Button
                                 dialog.cancel();
                             }
                         });

                 AlertDialog alert = builder.create();

                 alert.setTitle("Deleting All Secrets");
                 alert.show();

                    break;

        }
        return true;
    }
}
