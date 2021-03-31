package com.example.androidtaller1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("Está seguro de cerrar sesión?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = builder.create();
                titulo.setTitle("Confirme");
                titulo.show();
            }
        });

        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("App creada por:\nDaniel Fontalvo\nJulio Fuentes")
                        .setCancelable(false)
                        .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = builder.create();
                titulo.setTitle("Ayuda");
                titulo.show();
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