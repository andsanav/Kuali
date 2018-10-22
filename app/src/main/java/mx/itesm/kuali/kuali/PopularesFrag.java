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
    ArrayList<String> tiendas;
    ArrayList<String> redes;

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
        tiendas = new ArrayList<String>(){{add("Wal-Mart");
            add("https://www.walmart.com.ar/desodorante-antitraspirante-original-dove-85ml/p");}};
        redes = new ArrayList<String>(){{add("Facebook");
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
        id = 3;
        tiendas = new ArrayList<String>(){{add("Página oficial");
            add("https://www.rawapothecary.mx/products/fresh");}};
        redes = new ArrayList<String>(){{add("Facebook");
            add("https://www.facebook.com/RAWapothecary/?rc=p");}};
        nuevoProducto = new Producto(id, "Fresh", 180.1, "Raw Apothecary",
                "https://scontent.fmex10-2.fna.fbcdn.net/v/t1.0-9/15036397_1352643498102099_2686603416051963173_n.png?_nc_cat=110&_nc_ht=scontent.fmex10-2.fna&oh=84fb2a2226d662ec32ead30456cee391&oe=5C49A69D",
                new ArrayList<String>(){{add("https://cdn.shopify.com/s/files/1/1847/7875/products/Imagenes_sitio_11_1024x1024@2x.png?v=1539119820");}{add("https://cdn.shopify.com/s/files/1/1847/7875/products/EXPLICACION_PRODUCTOS_PAGINA_8_1024x1024@2x.jpg?v=1539118916");}{add("https://cdn.shopify.com/s/files/1/1847/7875/products/Imagenes_sitio_12_1024x1024@2x.png?v=1539119884");}},
                new ArrayList<CATEGORIAS>() {{add(CATEGORIAS.Salud_Belleza);}},
                new ArrayList<ArrayList<String>>() {{add(tiendas);}},
                new ArrayList<ArrayList<String>>() {{add(redes);}}, "Tónico de agua de rosas con un ingrediente secreto, aceite de geranio. La combinación floral, además de tener un olor increíble, hidrata, calma y desinflama la piel",
                0
        );
        myRef = database.getReference("Productos/" + id + "/");
        myRef.setValue(nuevoProducto);
        id = 4;
        tiendas = new ArrayList<String>(){{add("Página oficial");
            add("https://www.rawapothecary.mx/products/fix-it");}};
        redes = new ArrayList<String>(){{add("Facebook");
            add("https://www.facebook.com/RAWapothecary/?rc=p");}};
        nuevoProducto = new Producto(id, "Fresh", 430.1 , "Raw Apothecary",
                "https://scontent.fmex10-2.fna.fbcdn.net/v/t1.0-9/15036397_1352643498102099_2686603416051963173_n.png?_nc_cat=110&_nc_ht=scontent.fmex10-2.fna&oh=84fb2a2226d662ec32ead30456cee391&oe=5C49A69D",
                new ArrayList<String>(){{add("https://cdn.shopify.com/s/files/1/1847/7875/products/EXPLICACION_PRODUCTOS_PAGINA_9_1024x1024@2x.jpg?v=1529335887");}{add("https://cdn.shopify.com/s/files/1/1847/7875/products/Imagenes_sitio_10_1024x1024@2x.png?v=1539119411");}},
                new ArrayList<CATEGORIAS>() {{add(CATEGORIAS.Salud_Belleza);}},
                new ArrayList<ArrayList<String>>() {{add(tiendas);}},
                new ArrayList<ArrayList<String>>() {{add(redes);}}, "Controla el acné eliminando bacterias (aceite facial)",
                0
        );
        myRef = database.getReference("Productos/" + id + "/");
        myRef.setValue(nuevoProducto);
        id = 5;
        tiendas = new ArrayList<String>(){{add("Página oficial");
            add("https://www.rawapothecary.mx/productos/products/clear");}};
        redes = new ArrayList<String>(){{add("Facebook");
            add("https://www.facebook.com/RAWapothecary/?rc=p");}};
        nuevoProducto = new Producto(id, "Clear", 340.1 , "Raw Apothecary",
                "https://scontent.fmex10-2.fna.fbcdn.net/v/t1.0-9/15036397_1352643498102099_2686603416051963173_n.png?_nc_cat=110&_nc_ht=scontent.fmex10-2.fna&oh=84fb2a2226d662ec32ead30456cee391&oe=5C49A69D",
                new ArrayList<String>(){{add("https://cdn.shopify.com/s/files/1/1847/7875/products/Diseno_sin_titulo_4_1024x1024@2x.jpg?v=1527529789");}{add("https://cdn.shopify.com/s/files/1/1847/7875/products/Imagenes_sitio_1_530x@2x.png?v=1539097183");}},
                new ArrayList<CATEGORIAS>() {{add(CATEGORIAS.Salud_Belleza);}},
                new ArrayList<ArrayList<String>>() {{add(tiendas);}},
                new ArrayList<ArrayList<String>>() {{add(redes);}}, "Adiós puntos negros y barritos (mascarilla)",
                0
        );
        myRef = database.getReference("Productos/" + id + "/");
        myRef.setValue(nuevoProducto);

        id = 6;
        tiendas = new ArrayList<String>(){{add("Página oficial");
            add("https://www.rawapothecary.mx/productos/products/clear");}};
        redes = new ArrayList<String>(){{add("Facebook");
            add("https://www.facebook.com/RAWapothecary/?rc=p");}};
        nuevoProducto = new Producto(id, "Glow", 340.1 , "Raw Apothecary",
                "https://scontent.fmex10-2.fna.fbcdn.net/v/t1.0-9/15036397_1352643498102099_2686603416051963173_n.png?_nc_cat=110&_nc_ht=scontent.fmex10-2.fna&oh=84fb2a2226d662ec32ead30456cee391&oe=5C49A69D",
                new ArrayList<String>(){{add("https://cdn.shopify.com/s/files/1/1847/7875/products/Imagenes_sitio_3_1024x1024@2x.png?v=1539118349");}{add("https://cdn.shopify.com/s/files/1/1847/7875/products/Diseno_sin_titulo_5_1024x1024@2x.jpg?v=1527529807");}},
                new ArrayList<CATEGORIAS>() {{add(CATEGORIAS.Salud_Belleza);}},
                new ArrayList<ArrayList<String>>() {{add(tiendas);}},
                new ArrayList<ArrayList<String>>() {{add(redes);}}, "La manzanilla y el aceite esencial de lavanda te ayudarán a combatir los efectos de la contaminación y el sol en tu piel, restaurando la vitalidad y el brillo. ",
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
