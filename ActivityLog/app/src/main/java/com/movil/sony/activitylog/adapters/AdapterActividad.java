package com.movil.sony.activitylog.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.movil.sony.activitylog.db.ActividadDao;
import com.movil.sony.activitylog.modelo.Actividad;
import com.movil.sony.activitylog.R;

import java.util.List;

/**
 * Created by SONY on 07/04/2016.
 */
public class AdapterActividad  extends BaseAdapter {
    Context context;
    List<Actividad> actividades;
    ActividadDao dao;


    public AdapterActividad(Context context, List<Actividad> actividades) {
        this.context = context;
        this.actividades = actividades;
    }

    @Override
    public int getCount() {return actividades.size();}

    @Override
    public Object getItem(int position) {return actividades.get(position); }

    @Override
    public long getItemId(int position) { return  actividades.get(position).getId();}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null)
            v= View.inflate(context, R.layout.template_actividades,null);

        Actividad a = actividades.get(position);

        TextView nombreActividad = (TextView) v.findViewById(R.id.nombreActividad);
        nombreActividad.setText(a.getNombre());

        return v;
    }
}
