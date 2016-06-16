package com.movil.sony.activitylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClassLogeoF extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferen;
    private Button btncontinuar;
   // private EditText userF, passF;
    private TextInputLayout userF, passF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferen = getSharedPreferences("preferen",MODE_PRIVATE);
        boolean logeo = preferen.getBoolean("logeo",false);
        if(logeo){
            Intent i = new Intent(this, ClassLista.class);
            startActivity(i);
            finish();
        }
        setContentView(R.layout.logeo_f);

        btncontinuar=(Button) findViewById(R.id.btn_continuar);
       // userF = (EditText) findViewById(R.id.input_usuario);
       // passF = (EditText) findViewById(R.id.input_password);

        userF = (TextInputLayout) findViewById(R.id.input_usuario);
        passF = (TextInputLayout) findViewById(R.id.input_password);

        btncontinuar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
      //  String  us = userF.getText().toString();
       // String ps =   passF.getText().toString();

        String  us = userF.getEditText().getText().toString();
        String ps =   passF.getEditText().getText().toString();

        if(us.equals("") || ps.equals("")){
            Toast.makeText(this, " Usuario y contraseña de Facebook ", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent pantallaListaF = new Intent(ClassLogeoF.this,ClassLista.class);
            startActivity(pantallaListaF);

            SharedPreferences.Editor edit = preferen.edit();
            edit.putBoolean("logeo", true);
            edit.commit();
            finish();
        }

    }
}
