package com.example.hijeck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    DataBaseClassAdding db;
    String passupdate;
    EditText appname, username;
    AlertDialog builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialouge_view, null);
        builder = new AlertDialog.Builder(this).create();
        db = new DataBaseClassAdding(this);
        appname = (EditText) findViewById(R.id.application_name);
        username = (EditText) findViewById(R.id.user_name);
       final EditText passwordtext = (EditText) dialogView.findViewById(R.id.password_dialouge);

        builder.setIcon(R.drawable.logo);

        final Button update = (Button) dialogView.findViewById(R.id.update_btn);
        final Button cancel = (Button) dialogView.findViewById(R.id.cancle_btn);

        Button Search = (Button) findViewById(R.id.search_btn);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emailfield = db.checkUserNameWithFireld(appname.getText().toString(), username.getText().toString());
                if (!emailfield) {
                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (passwordtext.getText().toString().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Empty Field", Toast.LENGTH_SHORT).show();

                            } else {
                                boolean updateBool = db.update(appname.getText().toString(), username.getText().toString(), passwordtext.getText().toString());
                                if (updateBool) {
                                    builder.dismiss();
                                    Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder.dismiss();
                        }
                    });
                    builder.setView(dialogView);
                    builder.show();
                } else {
                    Toast.makeText(getApplicationContext(), "No secrets found", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        builder.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        builder.dismiss();
    }
}
