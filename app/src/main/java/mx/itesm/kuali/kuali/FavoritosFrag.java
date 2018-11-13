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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class FavoritosFrag extends Fragment {

    LinkedList<Producto> list = new LinkedList<>();
    RecyclerView recycle;
    View v;
    ArrayList<String> tiendas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_populares, container, false);
        recycle = (RecyclerView) v.findViewById(R.id.recycle);
        cargarRegistros();
        if(list.size() > 0){
            v = inflater.inflate(R.layout.fragment_cat_display, container, false);
            recycle = (RecyclerView) v.findViewById(R.id.recycle_categoria);
            Log.i("Pantalla Favoritos", Categoria.elementos_likes.toString());
            startRecycler();
        } else {
            v = inflater.inflate(R.layout.fragment_no_products, container, false);
        }
        return v;
    }

    private void startRecycler(){
        Log.i("context:", getActivity().getBaseContext().toString());
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,getActivity());
        RecyclerView.LayoutManager recycleMgr = new GridLayoutManager(getContext(),2);
        recycle.setLayoutManager(recycleMgr);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
    }

    public void cargarRegistros(){
        Log.i("SERVIO", Categoria.elementos_likes.size()+"");
        list.clear();
        for(Producto producto: Producto.lista_productos){
            if(Categoria.elementos_likes.contains(producto.getId()+""))
                list.add(producto);
        }
        startRecycler();
    }

}
