package com.example.andrea.recyclerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
        cargarRegistros(myRef);
        return v;
    }

    public void startRecycler(){
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

    public void cargarRegistros(DatabaseReference myRef){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<Producto>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    HashMap a = (HashMap) dataSnapshot1.getValue();
                    Integer id = Integer.parseInt(a.get("id").toString());
                    String nombre = (String) a.get("nombre");
                    Double precio_promedio = (Double) a.get("precio");
                    String marca = (String) a.get("marca");
                    String url_marca_logo = (String) a.get("url_marca_logo");
                    ArrayList<String> url_imagenes = (ArrayList<String>) a.get("url_imagenes");
                    ArrayList<CATEGORIAS> categorias = (ArrayList<CATEGORIAS>) a.get("categorias");
                    ArrayList<ArrayList<String>> url_tiendas = (ArrayList<ArrayList<String>>) a.get("url_tiendas");
                    ArrayList<ArrayList<String>> url_sociales = (ArrayList<ArrayList<String>>) a.get("url_sociales");
                    String descripcion = (String) a.get("descripcion");
                    Integer likes = Integer.parseInt(a.get("numLikes").toString());
                    Producto producto = new Producto(id, nombre, precio_promedio, marca, url_marca_logo, url_imagenes, categorias,
                            url_tiendas, url_sociales, descripcion, likes);
                    list.add(producto);

                }
                startRecycler();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Error:", error.toException());
            }
        });
    }
}
