package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FuerzaActivity extends AppCompatActivity implements View.OnClickListener {
    Button calcular;
    EditText numaceleracion, numasa;
    TextView resulFuerza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerza);
        calcular=findViewById(R.id.btncalFuerza);
        numaceleracion=findViewById(R.id.aceleracion);
        numasa=findViewById(R.id.masa);
        resulFuerza=findViewById(R.id.resultadoVelocidad);
        calcular.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncalFuerza:
                double ace, mas, resultado;
                ace= Integer.parseInt(numaceleracion.getText().toString());
                mas=Integer.parseInt(numasa.getText().toString());
                resultado=mas*ace;
                resulFuerza.setText("La aceleración del objeto es "+resultado+"Newton");
                break;
        }
    }
}