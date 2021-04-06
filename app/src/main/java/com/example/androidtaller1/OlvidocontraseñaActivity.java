package com.example.androidtaller1;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    Session session;
    int codigoVerificar;

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
                    int codigoRandom=(int) (Math.random() * 9999);
                    codigoVerificar=codigoRandom;
                    StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().build();
                    StrictMode.setThreadPolicy(policy);
                    Properties properties= new Properties();
                    properties.put("mail.smtp.host","smtp.googlemail.com");
                    properties.put("mail.smtp.starttls.enable","true");
                    properties.put("mail.smtp.socketFactory.port","465");
                    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                    properties.put("mail.smtp.auth","true");
                    properties.put("mail.smtp.port","465");
                    try{
                        session=Session.getDefaultInstance(properties, new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(correo,contraseña);
                            }
                        });
                        if(session!=null){
                            Message message= new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo));
                            message.setSubject("Recuperar contraseña");
                            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("juliojavif@gmail.com"));
                            message.setContent(codigoVerificar,"text/html; charset=utf-8");
                            Transport.send(message);
                            //Toast.makeText(getApplicationContext(), "Enviado", Toast.LENGTH_SHORT).show();
                        }

                    }catch (MessagingException e){
                        //e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Correo no existe", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnverificar:
                if(!txtcodigo1.getText().toString().isEmpty()){
                    int codigoverificador=Integer.parseInt(txtcodigo1.getText().toString());
                    if(codigoverificador==codigoVerificar){
                        Intent i=new Intent(getApplicationContext(),CambiarContraseActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Codigo incorrecto", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "No ha escrito ningun codigo", Toast.LENGTH_LONG).show();
                }

        }

    }
}