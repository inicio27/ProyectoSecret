package com.movil.sony.activitylog;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

public class ClassSplash extends AppCompatActivity {

    public static final int segundos=4;
    public static final int milisegundos = segundos*1000;
    public static final int retardo=2;
    private ProgressBar progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        progreso = (ProgressBar) findViewById(R.id.progreso);
        progreso.setMax(maximoProgreso());
        inicioAnimacion();
    }

    private void inicioAnimacion() {

        new CountDownTimer(milisegundos,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                progreso.setProgress(establecerProgreso(millisUntilFinished));

            }

            @Override
            public void onFinish() {
                Intent pantallaLogeo = new Intent(ClassSplash.this,Classlogeo.class);
                startActivity(pantallaLogeo);
                ClassSplash.this.finish();

            }
        }.start();
    }

    public int establecerProgreso(long miliseconds){
        return (int)((milisegundos-miliseconds)/1000);
    }

    public int maximoProgreso(){
        return segundos-retardo;
    }
}
