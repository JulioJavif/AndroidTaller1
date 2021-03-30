package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FisicaActivity extends AppCompatActivity implements View.OnClickListener {
    Button velocidad,fuerza,voltaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica);
        voltaje=findViewById(R.id.btnVoltaje);
        fuerza=findViewById(R.id.btnFuerza);
        velocidad=findViewById(R.id.btnVelocidad);
        velocidad.setOnClickListener(this);
        fuerza.setOnClickListener(this);
        voltaje.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnVelocidad:
                i= new Intent(getApplicationContext(),VelocidadActivity.class);
                startActivity(i);
                break;
            case R.id.btnFuerza:
                i= new Intent(getApplicationContext(),FuerzaActivity.class);
                startActivity(i);
                break;
        }
    }
}