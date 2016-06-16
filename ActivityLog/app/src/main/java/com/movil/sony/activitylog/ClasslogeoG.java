package com.movil.sony.activitylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClasslogeoG extends AppCompatActivity implements View.OnClickListener {

    private Button btncontinarG;
    SharedPreferences preferen;
    //private EditText userG,passG;
    private TextInputLayout userG, passG;

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
        setContentView(R.layout.logeo_g);

        btncontinarG=(Button) findViewById(R.id.btn_continuar);
       // userG = (EditText) findViewById(R.id.input_usuario);
       // passG = (EditText) findViewById(R.id.input_password);
         userG = (TextInputLayout) findViewById(R.id.input_usuario);
         passG = (TextInputLayout) findViewById(R.id.input_password);

        btncontinarG.setOnClickListener(this);

        /*btncontinarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaListaG = new Intent(ClasslogeoG.this,ClassLista.class);
                startActivity(pantallaListaG);

            }
        });*/
    }

    @Override
    public void onClick(View v) {
       // String usG = userG.getText().toString();
       // String psG = passG.getText().toString();
        String usG = userG.getEditText().getText().toString();
        String psG = passG.getEditText().getText().toString();

        if(usG.equals("")||psG.equals("")){
            Toast.makeText(this,"Usuario y contraseña de Google+",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent pantallaListaG = new Intent(ClasslogeoG.this,ClassLista.class);
            startActivity(pantallaListaG);

            SharedPreferences.Editor edit = preferen.edit();
            edit.putBoolean("logeo", true);
            edit.commit();
            finish();
        }
    }
}
