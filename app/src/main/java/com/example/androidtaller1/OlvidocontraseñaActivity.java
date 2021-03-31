package com.example.androidtaller1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OlvidocontraseñaActivity extends AppCompatActivity implements View.OnClickListener {
     Button codigo, verificar;
     EditText txtcorreo,txtcodigo1;
    DAOUsuario dao;
    String correo,contraseña;
    int codigoVerificar;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidocontra);
        txtcorreo=findViewById(R.id.correorecu);
        txtcodigo1=findViewById(R.id.codigo);
        codigo=findViewById(R.id.btncodigo);
        verificar=findViewById(R.id.btnverificar);
        dao=  new DAOUsuario(this);
        correo="cursosistema2021@gmail.com";
        contraseña="1478965@sistemas";
        codigo.setOnClickListener(this);
        verificar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncodigo:
                String correo = txtcorreo.getText().toString();
                if(correo==""){
                    Toast.makeText(getApplicationContext(), "No ha escrito un correo", Toast.LENGTH_LONG).show();
                }else if(dao.verificarCorreo(correo)==1){
                    int codigoRandom=(int) (Math.random() * 9999+1000);
                    codigoVerificar=codigoRandom;
                    AlertDialog.Builder builder = new AlertDialog.Builder(OlvidocontraseñaActivity.this);
                    Usuario usr = dao.UserByCorreo(correo);
                    usuario = usr;
                    builder.setMessage("Hola "+usr.getNombre()+" tu código es: "+codigoVerificar)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = builder.create();
                    titulo.setTitle("Su código");
                    titulo.show();
                }else{
                    Toast.makeText(getApplicationContext(), "Correo no existe", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnverificar:
                if(!txtcodigo1.getText().toString().isEmpty()){
                    int codigoverificador=Integer.parseInt(txtcodigo1.getText().toString());
                    if(codigoverificador==codigoVerificar){
                        Intent i=new Intent(getApplicationContext(),CambiarContraseActivity.class);
                        i.putExtra("id", usuario);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Codigo incorrecto", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "No ha escrito ningun codigo", Toast.LENGTH_LONG).show();
                }

        }

    }
}