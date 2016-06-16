package com.movil.sony.activitylog.modelo;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by SONY on 03/04/2016.
 */
public class Actividad {

    private String nombre,fecha;
    private long id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void  setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getFecha(){
        return fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String obtenerFecha() {
        String fecha;
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        String dia, mes, annio, hora, minuto, segundo;

        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        minuto = Integer.toString(c.get(Calendar.MINUTE));
        segundo = Integer.toString(c.get(Calendar.SECOND));

        fecha = dia+"/"+mes+"/"+annio+"   " + hora + ":"+ minuto +":"+ segundo;

        return fecha;

    }
}
