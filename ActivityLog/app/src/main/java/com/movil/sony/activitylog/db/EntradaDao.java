package com.movil.sony.activitylog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.movil.sony.activitylog.modelo.Entrada;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SONY on 30/05/2016.
 */
public class EntradaDao {

    public static final String  TABLE_ENT = "entrada";
    public static final String  ID_EN = "_idAEN";
    public static final String  NAME_EN= "NomEN";


    SQLiteDatabase db;


    public EntradaDao (Context context){
        ConexionDb con = new ConexionDb(context);
        db = con.getWritableDatabase();
    }

    public void insertarEntrada (Entrada entrada) {
        ContentValues cV = new ContentValues();
        cV.put(NAME_EN, entrada.getEntrada());
        long id = db.insert(TABLE_ENT,null,cV);
        entrada.setIdProcess(id);
    }

    public List<Entrada> getAllEntradas(){
        Cursor c = db.rawQuery("SELECT * FROM entrada ", null);
        return cursorEntList(c);
    }


    public List<Entrada> getAllEntradasD(long id){
        Cursor c = db.rawQuery("SELECT entrada.* FROM actividad,entrada WHERE actividad._idACT =  entrada._idACT AND actividad._idACT="+id, null);
        return cursorEntList(c);
    }


    public Entrada cursorEnt(Cursor c){
        Entrada entrada = null;
        if (c.moveToNext()){
            entrada = new Entrada();
            entrada.setIdProcess(c.getLong(0));
            entrada.setEntrada(c.getString(1));
        }
        return entrada;
    }


    private List<Entrada> cursorEntList(Cursor c)     {

        List<Entrada> data = new ArrayList<>();
        for(int i=0; i<c.getCount();i++){
            Entrada en = cursorEnt(c);
            data.add(en);
        }
        return data;
    }

    /*private List<Entrada> cursorActList(Cursor c){

        List<Entrada> data = new ArrayList<>();
        for(int i=0; i<c.getCount();i++){
            Entrada act = cursorEnt(c);
            data.add(act);
        }
        return data;
    }*/
}
