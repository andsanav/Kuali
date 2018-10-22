package mx.itesm.kuali.kuali;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatDisplayFragment extends PopularesFrag {

    RecyclerView recycle;
    View v;
    LinkedList<Producto> lista_filtrada = new LinkedList<>();

    public CatDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        filtrar_productos();
        v = inflater.inflate(R.layout.fragment_cat_display, container, false);
        recycle = (RecyclerView) v.findViewById(R.id.recycle_categoria);
        startRecycler();
        return v;
    }

    private void startRecycler(){
        Log.i("SERVIO", getActivity().getBaseContext().toString());
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(lista_filtrada, getActivity().getBaseContext());
        RecyclerView.LayoutManager recycleMgr = new GridLayoutManager(getContext(),2);
        recycle.setLayoutManager(recycleMgr);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
    }

    private void filtrar_productos(){
        String busqueda = "";
        switch(Categoria.categoria_busqueda){
            case 1:
                busqueda = "Mascotas"; break;
            case 2:
                busqueda = "Alimentos"; break;
            case 3:
                busqueda = "Hogar"; break;
            case 4:
                busqueda = "Cosmeticos"; break;
            case 5:
                busqueda = "Ropa_Accesorios"; break;
            case 6:
                busqueda = "Salud_Belleza"; break;
            case 7:
                busqueda = "Juguetes"; break;
            case 8:
                busqueda = "Artesanias"; break;
            case 9:
                busqueda = "Tecnologia"; break;
        }
        for(Producto producto: Producto.lista_productos){
            if(producto.getCategorias().contains(busqueda)){
                lista_filtrada.add(producto);
            }
        }
    }

}
