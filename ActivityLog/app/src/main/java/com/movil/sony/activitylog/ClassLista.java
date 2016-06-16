package com.movil.sony.activitylog;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.movil.sony.activitylog.adapters.AdapterActividad;
import com.movil.sony.activitylog.db.ActividadDao;
import com.movil.sony.activitylog.db.ConexionDb;
import com.movil.sony.activitylog.httpConection.HttpAsyncTask;
import com.movil.sony.activitylog.modelo.Actividad;
import com.movil.sony.activitylog.util.A;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ClassLista extends AppCompatActivity implements DialogInterface.OnClickListener,AdapterView.OnItemClickListener, View.OnClickListener, HttpAsyncTask.onHttpResponse {

    SharedPreferences preferen;
    private ImageButton btnmas, btnListarFinalizadas;
    private Button closeSesion;
    private ListView listaA;
    private AdapterActividad adapterA;

    ActividadDao dao;
    List<Actividad> lista;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        preferen = getSharedPreferences("preferen", MODE_PRIVATE);
        closeSesion = (Button) findViewById(R.id.cerrarSesion);
        btnListarFinalizadas = (ImageButton) findViewById(R.id.Afinalizadas);
        listaA = (ListView) findViewById(R.id.listaActividades);
        A.actividades = new ArrayList<>();
        adapterA = new AdapterActividad(this,A.actividades);

        listaA.setAdapter(adapterA);
        listaA.setOnItemClickListener(this);


        lista = new ArrayList<>();
        dao = new ActividadDao(this);
        mostrar();

        registerForContextMenu(listaA);

        //Codigo que trae desde la base de datos remota la lista de Actividades almacenadas
        String url = getString(R.string.url)+ getString(R.string.url_get);
        HttpAsyncTask task = new HttpAsyncTask(HttpAsyncTask.GET,this);
        task.execute(url);

        btnmas= (ImageButton) findViewById(R.id.btn_mas);



        btnmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaRegistroActividad = new Intent(ClassLista.this, ClassRegistroActividad.class);
                startActivity(pantallaRegistroActividad);
            }
        });

        closeSesion.setOnClickListener(this);

        btnListarFinalizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent pantallaFinalizadas = new Intent(ClassLista.this, ClassActividadesFinalizadas.class);
                //startActivity(pantallaFinalizadas);
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mostrar();
       // adapterA.notifyDataSetChanged();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ClassRegistro.class);
        intent.putExtra(ClassRegistro.POSICION, position);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        SharedPreferences.Editor editor =  preferen.edit();
        editor.putBoolean("logeo", false);
        editor.commit();

        Intent intent = new Intent(this, ClassLogeoF.class);
        startActivity(intent);
        finish();

    }


    private void mostrar() {
        A.actividades.clear();
       lista =dao.getAll();
        if(lista.size()==0) {
            Actividad ac = new Actividad();
            ac.setNombre("Actividades ");
            String fec = ac.obtenerFecha();
            ac.setFecha(fec);
            dao.insertar(ac);
            lista = dao.getAll();
        }
            for (Actividad ac : lista){
                A.actividades.add(ac); }
        adapterA.notifyDataSetChanged();
    }


    @Override
    public void onResponse(String response,int error) {
        Gson gson = new Gson();
        //Actividad ac = gson.fromJson(response,Actividad.class);
        Type type = new TypeToken<List<Actividad>>(){}.getType();
        List<Actividad> listaAct = gson.fromJson(response, type);
        for(Actividad ac : listaAct){
            lista.add(ac);
        }
        adapterA.notifyDataSetChanged();

        /*try {
            JSONObject json = new JSONObject(response);
            JSONObject query = json.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject channel = results.getJSONObject("channel");
            JSONObject atmosphere = channel.getJSONObject("atmosphere");

            String humedad = atmosphere.getString("humidity");
            String presion = atmosphere.getString("pressure");

            //Ahora debemos de asignar los valores obtenidos desde el objeto json
            // y fijarlos en el adapter eso es lo que falta para terminar con el proyecto


        }catch (JSONException e) {

            e.printStackTrace();
        }*/

    }

}
