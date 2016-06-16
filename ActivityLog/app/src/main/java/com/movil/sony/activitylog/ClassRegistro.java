package com.movil.sony.activitylog;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.movil.sony.activitylog.adapters.AdapterEntrada;
import com.movil.sony.activitylog.db.ActividadDao;
import com.movil.sony.activitylog.db.EntradaDao;
import com.movil.sony.activitylog.modelo.Actividad;
import com.movil.sony.activitylog.modelo.Entrada;
import com.movil.sony.activitylog.util.A;
import java.util.ArrayList;
import java.util.List;


public class ClassRegistro extends AppCompatActivity {

    public static final String POSICION = "pos";
    private TextView nombreAc, fechaAc;
    private ImageButton btnmasR, btnEliminar;
    //private Button btnEliminar;
    private ListView listaE;
    private AdapterEntrada adapterE;

    ActividadDao dao;


    EntradaDao daoE;
    List<Entrada> listaEn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        btnEliminar = (ImageButton) findViewById(R.id.btn_eliminar);
       // btnEliminar = (Button) findViewById(R.id.btn_eliminar);
        btnmasR = (ImageButton) findViewById(R.id.btn_masR);
        nombreAc = (TextView) findViewById(R.id.txtnombreActividad);
        fechaAc = (TextView) findViewById(R.id.txtFechaActividad);
        listaE = (ListView) findViewById(R.id.listaRegistro);

        A.entradas = new ArrayList<>();
        adapterE = new AdapterEntrada(this, A.entradas);
        listaE.setAdapter(adapterE);


        adapterE.notifyDataSetChanged();

        listaEn = new ArrayList<>();
        daoE = new EntradaDao(this);
        //mostrarE();

        dao = new ActividadDao(this);


        registerForContextMenu(listaE);

        final int pos = getIntent().getIntExtra(POSICION, 0);
        final String POSICION = "pos";

        final Actividad ac = A.actividades.get(pos);
        nombreAc.setText(ac.getNombre());
        fechaAc.setText(ac.getFecha());


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.eliminar(ac.getId());
                final Actividad ac = A.actividades.remove(pos);
                finish();
            }
        });


        btnmasR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaEntrada = new Intent(ClassRegistro.this, ClassEntrada.class);
                startActivity(pantallaEntrada);
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        //mostrarE();
        adapterE.notifyDataSetChanged();
    }


   /*private void mostrarE()  {
        A.entradas.clear();
        //listaEn =daoE.getAllEntradasD();
        //if(listaEn.size()==0) {
            Entrada en = new Entrada();
            en.setEntrada("Entrada para Actividades ");
         //   daoE.insertarEntrada(en);
          //  listaEn = daoE.getAllEntradasD();
        //}
        //for (Entrada e : listaEn){
          //  A.entradas.add(e); }
        adapterE.notifyDataSetChanged();
    }*/


}
