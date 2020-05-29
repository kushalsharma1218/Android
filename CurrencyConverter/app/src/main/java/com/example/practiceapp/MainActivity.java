package com.example.practiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText currency_in_dollar;
    private Button convert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currency_in_dollar = findViewById(R.id.currency_to_converter);
        convert = findViewById(R.id.convert_btn);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double currency = Double.valueOf(currency_in_dollar.getText().toString());
                Toast.makeText(MainActivity.this,""+currency * 75.61,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
