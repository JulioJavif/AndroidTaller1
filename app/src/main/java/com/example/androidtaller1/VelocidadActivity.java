package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VelocidadActivity extends AppCompatActivity implements View.OnClickListener {
    Button calcular;
    EditText numdistancia, numtiempo;
    TextView resultadoVelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidad);
        calcular=findViewById(R.id.btncalVelocidad);
        numdistancia=findViewById(R.id.distancia);
        numtiempo=findViewById(R.id.tiempo);
        resultadoVelo=findViewById(R.id.resultadoVelocidad);
        calcular.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncalVelocidad:
                double dis= Integer.parseInt(numdistancia.getText().toString());
                double tie=Integer.parseInt(numtiempo.getText().toString());
                double resultado=dis/tie;
                resultadoVelo.setText("La velocida es "+resultado+"m/s");
                break;
        }
    }
}