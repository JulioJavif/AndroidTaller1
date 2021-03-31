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
                if (!numdistancia.getText().toString().isEmpty()&&!numtiempo.getText().toString().isEmpty()){
                    double dis= Double.parseDouble(numdistancia.getText().toString());
                    double tie= Double.parseDouble(numtiempo.getText().toString());
                    double resultado=dis/tie;
                    if (tie<=0){
                        resultadoVelo.setText("No hay velocidad");
                    }else {
                        resultadoVelo.setText("La velocida es "+resultado+" m/s");
                    }
                }else {
                    resultadoVelo.setText("Llene los campos");
                }
                break;
        }
    }
}