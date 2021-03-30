package com.example.androidtaller1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class TextoActivity extends AppCompatActivity {

    CheckBox negrita, italica, subrayar;
    EditText texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);

        texto = findViewById(R.id.edttexto);
        negrita = findViewById(R.id.cbxnegrita);
        italica = findViewById(R.id.cbxitalica);
        subrayar = findViewById(R.id.cbxsubrayada);

        negrita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(negrita.isChecked()){
                    texto.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
                    subrayar.setChecked(false);
                }else{
                    texto.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                }
            }
        });

        italica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (italica.isChecked()){
                    texto.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }else {
                    texto.setTypeface(Typeface.DEFAULT);
                }
            }
        });

        subrayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subrayar.isChecked()){
                    texto.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    negrita.setChecked(false);
                }else {
                    texto.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                }
            }
        });
    }
}