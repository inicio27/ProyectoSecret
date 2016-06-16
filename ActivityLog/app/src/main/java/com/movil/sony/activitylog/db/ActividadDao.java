package com.movil.sony.activitylog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.movil.sony.activitylog.modelo.Actividad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 29/05/2016.
 */
public class ActividadDao {

    public static final String  NAME_BD = "Actividades";
    public static final String  TABLE_ACT = "actividad";
    public static final String  ID_ACT = "_idACT";
    public static final String  NAME_ACT= "NomACT";
    public static final String  C_FECHA = "columFech";

    SQLiteDatabase db;

    public ActividadDao (Context context){
        ConexionDb con = new ConexionDb(context);
        db = con.getWritableDatabase();
    }

    public void insertar (Actividad actividad) {
        ContentValues cV = new ContentValues();
        cV.put(NAME_ACT, actividad.getNombre());
        cV.put(C_FECHA, actividad.getFecha());
        long id = db.insert(TABLE_ACT,null,cV);
        actividad.setId(id);
    }

    public  void eliminar(long id){

        db.delete(TABLE_ACT, ID_ACT +" ="+id,null);
    }

    public List<Actividad> getAll(){
        Cursor c = db.rawQuery("SELECT * FROM actividad ", null);
        return cursorActList(c);
    }

    public Actividad cursorAct(Cursor c){
        Actividad actividad = null;
        if (c.moveToNext()){
            actividad = new Actividad();
            actividad.setId(c.getLong(0));
            actividad.setNombre(c.getString(1));
            actividad.setFecha(c.getString(2));
        }
        return actividad;
    }

    private List<Actividad> cursorActList(Cursor c){

        List<Actividad> data = new ArrayList<>();
        for(int i=0; i<c.getCount();i++){
            Actividad act = cursorAct(c);
            data.add(act);
        }
        return data;
    }
}
