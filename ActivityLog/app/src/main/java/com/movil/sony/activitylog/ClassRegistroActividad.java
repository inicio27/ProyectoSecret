package com.movil.sony.activitylog;



import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.movil.sony.activitylog.adapters.AdapterActividad;
import com.movil.sony.activitylog.db.ActividadDao;
import com.movil.sony.activitylog.db.ConexionDb;
import com.movil.sony.activitylog.httpConection.HttpAsyncTask;
import com.movil.sony.activitylog.modelo.Actividad;
import com.movil.sony.activitylog.util.A;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class ClassRegistroActividad extends AppCompatActivity implements View.OnClickListener, HttpAsyncTask.onHttpResponse {

    //private EditText inputNombre;
    private Button btnguardar;
    private TextInputLayout inputNombre;


    ActividadDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_actividad);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //inputNombre = (EditText) findViewById(R.id.input_nombreActividad);
        btnguardar=(Button) findViewById(R.id.btn_guardar);
        inputNombre = (TextInputLayout) findViewById(R.id.input_nombreActividad);
        btnguardar.setOnClickListener(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        String nom = inputNombre.getEditText().getText().toString();

        //Validar la fecha que sea en formato YYYY/MM/DD
        if (nom.equals("")) {
                Toast.makeText(this, " Nombre de la actividad ", Toast.LENGTH_SHORT).show();
        }
       else {

           Actividad ac = new Actividad();
            ac.setNombre(nom);
            String fecha = ac.obtenerFecha();
            ac.setFecha(fecha);
            //Log.i("ID","" + ac.getId());
            dao = new ActividadDao(this);
            dao.insertar(ac);

            String url = getString(R.string.url)+getString(R.string.url_post);
            JsonObject json = new JsonObject();
            json.addProperty("NomACT",nom);
            json.addProperty("columFech",fecha);

            HttpAsyncTask task = new HttpAsyncTask(HttpAsyncTask.POST,this);
            task.execute(url, json.toString());


        }
            finish();



    }

    @Override
    public void onResponse(String response, int error) {
        try {
            JSONObject json = new JSONObject(response);

            boolean success = json.getBoolean("success");
            if(success){
                Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                //finish();
            }else{
                Toast.makeText(this,"Error al registrar actividad",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
