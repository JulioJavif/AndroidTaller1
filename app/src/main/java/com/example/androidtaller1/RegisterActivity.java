package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spiner;
    String text;

    Button guardar, regresar;
    EditText txtNombre, txtApellido, txtCorreo, txtContrasena;
    CheckBox aceptarTerm;
    DAOUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spiner=(Spinner) findViewById(R.id.spinersexo);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.opsexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);

        spiner.setOnItemSelectedListener(this);
        guardar = findViewById(R.id.btnguardar);
        txtNombre = findViewById(R.id.edtnombre);
        txtApellido = findViewById(R.id.edtapellido);
        txtCorreo = findViewById(R.id.edtcorreo);
        txtContrasena = findViewById(R.id.edtpassword);
        aceptarTerm = findViewById(R.id.cbxaceptarterm);
        regresar = findViewById(R.id.btnregresar);
        guardar.setEnabled(false);

        dao = new DAOUsuario(this);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u = new Usuario();
                u.setNombre(txtNombre.getText().toString());
                u.setApellido(txtApellido.getText().toString());
                u.setCorreo(txtCorreo.getText().toString());
                u.setContrasena(txtContrasena.getText().toString());
                u.setSexo(spiner.getSelectedItem().toString());
                if (!u.getContrasena().isEmpty() && u.getContrasena().length()>5){
                    if (u.isNull()){
                        Toast.makeText(RegisterActivity.this, "Error: campos vacíos", Toast.LENGTH_LONG).show();
                    }else if (dao.insertUsuario(u)){
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Usuario ya registrado", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "La contraseña debe tener mínimo 6 caracteres", Toast.LENGTH_SHORT).show();
                }
            }
        });

        aceptarTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aceptarTerm.isChecked()){
                    guardar.setEnabled(true);
                }else {
                    guardar.setEnabled(false);
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text=parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
