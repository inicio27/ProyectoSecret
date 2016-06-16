package com.movil.sony.activitylog.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.movil.sony.activitylog.modelo.Actividad;

/**
 * Created by SONY on 29/05/2016.
 */
public class ConexionDb extends SQLiteOpenHelper {

    public static final String  NAME_BD = "Actividades";
    public static final String  TABLE_ACT = "actividad";
    public static final String  ID_ACT = "_idACT";
    public static final String  NAME_ACT= "NomACT";
    public static final String  C_FECHA = "columFech";


    public static final String  TABLE_ENT = "entrada";
    public static final String  ID_EN = "_idAEN";
    public static final String  NAME_EN= "NomEN";

    public static  int  VERSION = 1;




    public ConexionDb(Context context) {
        super(context, NAME_BD, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_ACT+" ( "
                +ID_ACT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME_ACT+" TEXT, "+C_FECHA+" DATE"+" )");




        /*db.execSQL("CREATE TABLE "+TABLE_ENT+" ( "
                +ID_EN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME_EN+" TEXT )");*/

        db.execSQL(" CREATE TABLE "+TABLE_ENT+" ( "
                +ID_ACT+" INTEGER, "
                +ID_EN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME_EN+" TEXT, "+
                "FOREIGN KEY ( "+ID_ACT+" ) REFERENCES " + TABLE_ACT+" ( "+ID_ACT+" ) "+" )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_ACT);
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_ENT);
        onCreate(db);
    }

    public void close(){
        this.close();

    }

}
/*
        db.execSQL("CREATE TABLE "+TABLE_ENT+" ( "
        +ID_EN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
        +NAME_EN+" TEXT )");

* */