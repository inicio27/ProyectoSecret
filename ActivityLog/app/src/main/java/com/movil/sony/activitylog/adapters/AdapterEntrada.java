package com.movil.sony.activitylog.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.movil.sony.activitylog.modelo.Entrada;
import com.movil.sony.activitylog.R;

import java.util.List;

/**
 * Created by SONY on 23/04/2016.
 */
public class AdapterEntrada extends BaseAdapter {

    Context context;
    List<Entrada> entradas;

    public AdapterEntrada(Context context, List<Entrada> entradas) {
        this.context = context;
        this.entradas = entradas;
    }

    @Override
    public int getCount() { return entradas.size(); }

    @Override
    public Object getItem(int position) {  return entradas.get(position);  }

    @Override
    public long getItemId(int position) {  return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View c = convertView;
        if(c == null)
            c= View.inflate(context, R.layout.template_entrada,null);


        Entrada e = entradas.get(position);

        TextView nombreEntrada = (TextView) c.findViewById(R.id.nombreEntrada);

        nombreEntrada.setText(e.getEntrada());


        return c;
    }
}
