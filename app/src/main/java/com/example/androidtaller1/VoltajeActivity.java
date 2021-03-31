package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class VoltajeActivity extends AppCompatActivity {

    Spinner resistencias;
    EditText res1, res2, res3, total, amperaje;
    Button calcular;
    CheckBox paralelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltaje);

        resistencias = findViewById(R.id.spnresistencias);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (getApplicationContext(), R.array.resistencias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resistencias.setAdapter(adapter);

        res1 = findViewById(R.id.edtresistencia1);
        res2 = findViewById(R.id.edtresistencia2);
        res3 = findViewById(R.id.edtresistencia3);
        calcular = findViewById(R.id.btncalVoltaje);
        paralelas = findViewById(R.id.cbxparalelas);
        total = findViewById(R.id.edtresultadovol);
        amperaje = findViewById(R.id.edtamperaje);

        res1.setEnabled(false);
        res2.setEnabled(false);
        res3.setEnabled(false);
        total.setEnabled(false);

        resistencias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (resistencias.getSelectedItemPosition()==0){
                    res1.setEnabled(false);
                    res2.setEnabled(false);
                    res3.setEnabled(false);
                    res1.setText("");
                    res2.setText("");
                    res3.setText("");
                }else if (resistencias.getSelectedItemPosition()==1){
                    res1.setEnabled(true);
                    res2.setEnabled(true);
                    res3.setEnabled(false);
                    res3.setText("");
                }else {
                    res1.setEnabled(true);
                    res2.setEnabled(true);
                    res3.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paralelas.isChecked()){
                    if (    !res1.getText().toString().isEmpty()
                            &&!res2.getText().toString().isEmpty()
                            &&!res3.getText().toString().isEmpty()
                            &&resistencias.getSelectedItemPosition()==2
                            &&!amperaje.getText().toString().isEmpty()){
                        double r1 = Double.parseDouble(res1.getText().toString());
                        double r2 = Double.parseDouble(res2.getText().toString());
                        double r3 = Double.parseDouble(res3.getText().toString());
                        double rtotal = (1/r1)+(1/r2)+(1/r3);
                        double amp = Double.parseDouble(amperaje.getText().toString());
                        rtotal = 1/rtotal;
                        total.setText(""+rtotal*amp+"V");
                    }else if (!res1.getText().toString().isEmpty()
                            &&!res2.getText().toString().isEmpty()
                            &&resistencias.getSelectedItemPosition()==1
                            &&!amperaje.getText().toString().isEmpty()){
                        double r1 = Double.parseDouble(res1.getText().toString());
                        double r2 = Double.parseDouble(res2.getText().toString());
                        double rtotal = (1/r1)+(1/r2);
                        rtotal = 1/rtotal;
                        double amp = Double.parseDouble(amperaje.getText().toString());
                        total.setText(""+rtotal*amp+"V");
                    }else {
                        total.setText("Debe llenar los campos.");
                    }
                }else {
                    if (    !res1.getText().toString().isEmpty()
                            &&!res2.getText().toString().isEmpty()
                            &&!res3.getText().toString().isEmpty()
                            &&resistencias.getSelectedItemPosition()==2
                            &&!amperaje.getText().toString().isEmpty()){
                        double r1 = Double.parseDouble(res1.getText().toString());
                        double r2 = Double.parseDouble(res2.getText().toString());
                        double r3 = Double.parseDouble(res3.getText().toString());
                        double rtotal = r1 + r2 + r3;
                        double amp = Double.parseDouble(amperaje.getText().toString());
                        total.setText(""+rtotal*amp+"V");
                    }else if (!res1.getText().toString().isEmpty()
                            &&!res2.getText().toString().isEmpty()
                            &&resistencias.getSelectedItemPosition()==1
                            &&!amperaje.getText().toString().isEmpty()){
                        double r1 = Double.parseDouble(res1.getText().toString());
                        double r2 = Double.parseDouble(res2.getText().toString());
                        double rtotal = r1 + r2;
                        double amp = Double.parseDouble(amperaje.getText().toString());
                        total.setText(""+rtotal*amp+"V");
                    }else {
                        total.setText("Debe llenar los campos.");
                    }
                }
            }
        });
    }
}