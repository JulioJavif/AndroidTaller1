package com.example.androidtaller1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CambiarContraseActivity extends AppCompatActivity {

    EditText ncontrasena, confncontrasena;
    Button confirmar;
    DAOUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiarcontrase);

        dao = new DAOUsuario(getApplicationContext());

        ncontrasena = findViewById(R.id.edtncontrasena);
        confncontrasena = findViewById(R.id.edtconfncontrasena);
        confirmar = findViewById(R.id.btncambiarpsw);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ncontrasena.getText().toString().isEmpty() && !confncontrasena.getText().toString().isEmpty()){
                    if (ncontrasena.getText().toString().length()>5){
                        if (ncontrasena.getText().toString().equals(confncontrasena.getText().toString())){
                            Usuario us =(Usuario) getIntent().getExtras().getSerializable("id");
                            //Toast.makeText(CambiarContraseActivity.this, ""+us.getId(), Toast.LENGTH_LONG).show();
                            dao.setContrasena(ncontrasena.getText().toString(), us.getId());
                            AlertDialog.Builder builder = new AlertDialog.Builder(CambiarContraseActivity.this);
                            builder.setMessage("Contraseña cambiada!")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent i = new Intent(CambiarContraseActivity.this, MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    });
                            AlertDialog titulo = builder.create();
                            titulo.setTitle("Notificación");
                            titulo.show();
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CambiarContraseActivity.this);
                            builder.setMessage("Las contraseñas no coinciden")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog titulo = builder.create();
                            titulo.setTitle("Notificación");
                            titulo.show();
                        }
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CambiarContraseActivity.this);
                        builder.setMessage("Contraseña muy corta")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog titulo = builder.create();
                        titulo.setTitle("Notificación");
                        titulo.show();
                    }
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CambiarContraseActivity.this);
                    builder.setMessage("Debe llenar los campos")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = builder.create();
                    titulo.setTitle("Notificación");
                    titulo.show();
                }
            }
        });
    }
}