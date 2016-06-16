package com.movil.sony.activitylog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.movil.sony.activitylog.db.ActividadDao;

public class Classlogeo extends AppCompatActivity {

    private ImageButton btnfacebook;
    private ImageButton btngoogle;
    private Button      btncontinuar;
    //private CardView    btnfacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logeo);

        btnfacebook = (ImageButton) findViewById(R.id.btn_facebook);
        //btnfacebook= (CardView) findViewById(R.id.btn_facebook);
        btngoogle = (ImageButton) findViewById(R.id.btn_google);
        btncontinuar = (Button) findViewById(R.id.btn_continuar);


        btnfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaLogeof = new Intent(Classlogeo.this, ClassLogeoF.class);
                startActivity(pantallaLogeof);
            }
        });


        btngoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaLogeog = new Intent(Classlogeo.this, ClasslogeoG.class);
                startActivity(pantallaLogeog);
            }
        });

        btncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pantallaListaActividades = new Intent(Classlogeo.this, ClassLista.class);
                startActivity(pantallaListaActividades);

            }
        });





    }
}
