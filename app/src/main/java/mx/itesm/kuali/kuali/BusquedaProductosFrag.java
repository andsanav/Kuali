package mx.itesm.kuali.kuali;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaProductosFrag extends PopularesFrag {


    RecyclerView recycle;
    View v;
    LinkedList<Producto> lista_filtrada = new LinkedList<>();

    public BusquedaProductosFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        filtrar_productos();
        if(lista_filtrada.size() == 0){
            v = inflater.inflate(R.layout.fragment_no_products, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_cat_display, container, false);
            recycle = (RecyclerView) v.findViewById(R.id.recycle_categoria);
            startRecycler();
        }
        return v;
    }

    private void startRecycler(){
        Log.i("SERVIO", getActivity().getBaseContext().toString());
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(lista_filtrada, getActivity());
        RecyclerView.LayoutManager recycleMgr = new GridLayoutManager(getContext(),2);
        recycle.setLayoutManager(recycleMgr);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
    }

    private void filtrar_productos(){
        lista_filtrada.clear();
        for(Producto producto: Producto.lista_productos){
            if(producto.getNombre().equalsIgnoreCase(Categoria.elemento_busqueda)){
                lista_filtrada.add(producto);
            }
        }
    }

}
