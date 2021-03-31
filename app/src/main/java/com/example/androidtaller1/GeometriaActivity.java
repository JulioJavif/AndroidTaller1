package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GeometriaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner spinner;
    EditText x1,y2,x2,y1;
    Button calcular;
    String text;
    TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometria);
        spinner= findViewById(R.id.spinnergeometria);
        calcular=findViewById(R.id.btncalcular);
        x1=findViewById(R.id.xnum1);
        y1=findViewById(R.id.ynum1);
        x2=findViewById(R.id.xnum2);
        y2=findViewById(R.id.ynum2);
        resultado=findViewById(R.id.resultado);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.opcoperacion, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        calcular.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btncalcular:
                if (!(x1.getText().toString().isEmpty() && y1.getText().toString().isEmpty() &&
                        x2.getText().toString().isEmpty() && y2.getText().toString().isEmpty())) {
                    double numx1, numy1, numx2, numy2;
                    numx1 = Double.parseDouble(x1.getText().toString());
                    numy1 = Double.parseDouble(y1.getText().toString());
                    numx2 = Double.parseDouble(x2.getText().toString());
                    numy2 = Double.parseDouble(y2.getText().toString());
                    if ("Cuadrante".equals(text)) {
                        resultado.setText("el punto 1 "+buscarCuadrante(numx1,numy2)+"/n"+"el punto 2 "+buscarCuadrante(numx2,numy2));
                    } else if ("Pendiente".equals(text)) {
                        double pendiente;
                        if((numx2-numx2)==0){
                            resultado.setText("pendiente indefinidad");
                        }else{
                            pendiente = (numy2 - numy1) / (numx2 - numx2);
                            resultado.setText("La pendientre entre los dos puntos es m=" + pendiente);
                        }

                    } else {
                        double distancia;
                        distancia = Math.sqrt(Math.pow((numx1 - numx2), 2) + Math.pow((numy1 - numy2), 2));
                        resultado.setText("La distancia entre los dos puntos es d=" + distancia);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Falta un numero", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public String buscarCuadrante(double num1,double num2){
         String defin="";
        if(num1==0 && num2==0){
            defin="se encuentra en el origen";
        }else if(num1>=0 && num2>=0){
            defin="se encuentra en el primer cuadrante";
        } else if(num1<0 && num2>=0){
            defin="se encuentra en el segundo cuadrante";

        }else if(num1<=0 &&  num2<0){
           defin="se encuentra en el tercer cuadrante";
        }else if(num1>0 && num2<0){
            defin="se encuentra en el cuarto cuadrante";
        }
      return defin;
    }
}