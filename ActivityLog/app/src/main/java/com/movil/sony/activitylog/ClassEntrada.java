package com.movil.sony.activitylog;


import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.movil.sony.activitylog.db.EntradaDao;
import com.movil.sony.activitylog.modelo.Entrada;
import com.movil.sony.activitylog.util.A;

public class ClassEntrada extends AppCompatActivity implements  View.OnClickListener{

    //private EditText nuevaEntrada;
    private Button btnGuardarR;
    private TextInputLayout nuevaEntrada;

    EntradaDao daoE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrada);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // nuevaEntrada = (EditText) findViewById(R.id.input_entrada);
        btnGuardarR=(Button) findViewById(R.id.btn_guardarR);
        nuevaEntrada = (TextInputLayout) findViewById(R.id.input_entrada);


        btnGuardarR.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //String input = nuevaEntrada.getText().toString();
        String input = nuevaEntrada.getEditText().getText().toString();

        if(input.equals("")){
            Toast.makeText(this,"Ingrese una entrada",Toast.LENGTH_SHORT).show();
        }
        else {
            Entrada en = new Entrada();
            en.setEntrada(input);
            //daoE = new EntradaDao(this);
            //daoE.insertarEntrada(en);
            A.entradas.add(en);
        }
        finish();
    }

}
