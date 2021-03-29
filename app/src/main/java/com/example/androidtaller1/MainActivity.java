package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button registro, ingresar;
    EditText txtCorreo, txtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro = findViewById(R.id.btnregistrar);
        ingresar = findViewById(R.id.btncontinuar);
        txtCorreo = findViewById(R.id.usuario);
        txtpass = findViewById(R.id.contraseña);
        DAOUsuario dao = new DAOUsuario(this);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtCorreo.getText().toString();
                String pass = txtpass.getText().toString();
                if (correo.equals("") || pass.equals("")){
                    Toast.makeText(getApplicationContext(), "Debe llenar los campos", Toast.LENGTH_LONG).show();
                }else if (dao.login(correo, pass) ==1){
                    Usuario us = dao.getUsuario(correo, pass);
                    Toast.makeText(getApplicationContext(), "Datos Correctos", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}