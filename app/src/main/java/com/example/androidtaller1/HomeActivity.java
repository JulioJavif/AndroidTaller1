package com.example.androidtaller1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    ImageButton cerrarS, ayuda;
    Button geometria, texto, fisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cerrarS = findViewById(R.id.imbsalir);
        ayuda = findViewById(R.id.imbayuda);
        geometria = findViewById(R.id.btngeometria);
        texto = findViewById(R.id.btntexto);
        fisica = findViewById(R.id.btnfisica);

        cerrarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Has Cerrado sesión", Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Confirme");
                builder.setMessage("Desea salir?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Hacer cosas aqui al hacer clic en el boton de aceptar
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                AlertDialog mostrar = builder.create();
                mostrar.show();
            }
        });

        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Has pedido ayuda", Toast.LENGTH_LONG).show();
                //AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                //builder.setTitle("Ayuda");
                //builder.setMessage("App taller 1\npor:\nJulio Fuentes\nDaniel Fontalvo");
                //builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    //@Override
                    //public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    //}
                //});
                //AlertDialog dialog = builder.create();
                //dialog.show();
            }
        });

        geometria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GeometriaActivity.class);
                startActivity(i);
            }
        });

        texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TextoActivity.class);
                startActivity(i);
            }
        });

        fisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FisicaActivity.class);
                startActivity(i);
            }
        });
    }
}