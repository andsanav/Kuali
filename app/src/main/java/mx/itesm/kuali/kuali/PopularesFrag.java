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
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class PopularesFrag extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Producto> list;
    RecyclerView recycle;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_populares, container, false);
        recycle = (RecyclerView) v.findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Productos");

        //agregarRegistros();
        cargarRegistros();
        return v;
    }

    private void startRecycler(){
        Log.i("context:", getActivity().getBaseContext().toString());
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,getActivity().getBaseContext());
        RecyclerView.LayoutManager recycleMgr = new GridLayoutManager(getContext(),2);
        recycle.setLayoutManager(recycleMgr);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
    }

    private void agregarRegistros(){
        Integer id = 1;
        final ArrayList<String> tiendas = new ArrayList<String>(){{add("Wal-Mart");
            add("https://www.walmart.com.ar/desodorante-antitraspirante-original-dove-85ml/p");}};
        final ArrayList<String> redes = new ArrayList<String>(){{add("FB");
            add("https://www.facebook.com/DoveMexico/");}};
        Producto nuevoProducto = new Producto(id, "Dove Desodorante", 74.6, "Dove",
                "https://cdns3-2.primor.eu/img/m/312.jpg",
                new ArrayList<String>(){{add("https://static.condisline.com/resize_395x416/images/catalog/large/930027.jpg");}},
                new ArrayList<CATEGORIAS>() {{add(CATEGORIAS.Cosmeticos);}},
                new ArrayList<ArrayList<String>>() {{add(tiendas);}},
                new ArrayList<ArrayList<String>>() {{add(redes);}}, "Desodorante en barra",
                0
        );
        myRef = database.getReference("Productos/" + id + "/");
        myRef.setValue(nuevoProducto);
        id = 2;
        nuevoProducto = new Producto(id, "Dove Desodorante", 74.6, "Dove",
                "https://cdns3-2.primor.eu/img/m/312.jpg",
                new ArrayList<String>(){{add("https://static.condisline.com/resize_395x416/images/catalog/large/930027.jpg");}},
                new ArrayList<CATEGORIAS>() {{add(CATEGORIAS.Cosmeticos);}},
                new ArrayList<ArrayList<String>>() {{add(tiendas);}{add(redes);}},
                new ArrayList<ArrayList<String>>() {{add(redes);}}, "Desodorante en barra",
                0
        );
        myRef = database.getReference("Productos/" + id + "/");
        myRef.setValue(nuevoProducto);
    }

    public void cargarRegistros(){
        list = Producto.lista_productos;
        Collections.sort(list, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return Integer.compare(p1.getNumLikes(), p2.getNumLikes());
            } } );
        Collections.reverse(list);
        startRecycler();
    }
}
