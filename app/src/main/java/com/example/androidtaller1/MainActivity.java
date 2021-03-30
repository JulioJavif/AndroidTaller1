package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button registro, ingresar, cambiopass;
    EditText txtCorreo, txtpass;
    CheckBox cbxrecordar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro = findViewById(R.id.btnregistrar);
        ingresar = findViewById(R.id.btncontinuar);
        cambiopass = findViewById(R.id.btncambioclave);
        txtCorreo = findViewById(R.id.usuario);
        txtpass = findViewById(R.id.contraseña);
        cargarPreferencias();
        cbxrecordar = findViewById(R.id.cbxrecordar);
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
                    if (cbxrecordar.isChecked()){
                        guardarPreferencias(correo, pass);
                        Toast.makeText(getApplicationContext(), "Preferencias guardadas", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(getApplicationContext(), "Inicio correcto, bienvenido: "+us.getNombre(), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    i.putExtra("id", us.getId());
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Correo o contraseña no válidos", Toast.LENGTH_LONG).show();
                }
            }
        });

        cambiopass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), OlvidocontraseñaActivity.class);
                startActivity(i);
            }
        });
    }

    private void guardarPreferencias(String correo, String pass){
        SharedPreferences pref = getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("correo", correo);
        editor.putString("pass", pass);

        editor.commit();
    }

    private void cargarPreferencias() {
        SharedPreferences pref = getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);

        String correo = pref.getString("correo", " ");
        String pass = pref.getString("pass", " ");

        //Toast.makeText(getApplicationContext(), "pass: "+pass, Toast.LENGTH_SHORT).show();
        txtCorreo.setText(correo);
        txtpass.setText(pass);
    }
}