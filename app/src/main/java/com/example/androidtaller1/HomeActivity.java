package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        //Toast.makeText(getApplicationContext(), "Su id es: "+id, Toast.LENGTH_SHORT).show();
    }
}