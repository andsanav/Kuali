package mx.itesm.kuali.kuali;

import android.content.Context;
import android.net.Uri;
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


public class CategoriasFrag extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    RecyclerView recycle;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_populares, container, false);
        recycle = (RecyclerView) v.findViewById(R.id.recycle);
        startRecycler();
        /*database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Categorias");

        //agregarRegistros();
        //if(Categoria.lista_categorias.size() == 0){
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        HashMap a = (HashMap) dataSnapshot1.getValue();
                        Categoria currentCat = new Categoria(Integer.valueOf(a.get("id").toString()), (String)a.get("nombre"), (String) a.get("url_thumbnail"));
                        Categoria.lista_categorias.add(currentCat);
                    }
                    startRecycler();
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Log.w("Error:", error.toException());
                }
            });*/
        //}
        return v;
    }

    public void startRecycler(){
        Log.i("context:", getActivity().getBaseContext().toString());
        RecyclerAdapterCat recyclerAdapter = new RecyclerAdapterCat(Categoria.lista_categorias,getActivity().getBaseContext());
        RecyclerView.LayoutManager recycleMgr = new GridLayoutManager(getContext(),2);
        recycle.setLayoutManager(recycleMgr);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
    }

    private void agregarRegistros(){
        Integer id = 1;
        Categoria nuevaCategoria = new Categoria(id, "Mascotas", "https://image.shutterstock.com/image-photo/pet-accessories-isolated-on-white-260nw-672084247.jpg"
        );
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 2;
        nuevaCategoria = new Categoria(id, "Alimentos", "https://img.elcomercio.pe/files/ec_article_multimedia_gallery/uploads/2017/11/03/59fcdc8504772.jpeg"
        );
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 3;
        nuevaCategoria = new Categoria(id, "Hogar", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRC6PHK8oMj7rLLQiHMdSXGcA31UOWmFKPtTAfLLoI58p_hCzzgXQ"
        );
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 4;
        nuevaCategoria = new Categoria(id, "Cosméticos", "https://aprendete.com/wp-content/uploads/2017/04/10-cosmeticos.jpg");
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 5;
        nuevaCategoria = new Categoria(id, "Ropa y accesorios", "https://mx.hola.com/imagenes/estar-bien/20180312121453/ropa-contamina-medio-ambiente/0-548-821/ropa-medioambiente-t.jpg"
        );
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 6;
        nuevaCategoria = new Categoria(id, "Salud y Belleza", "https://www.retailmenot.com/blog/wp-content/uploads/2015/04/feature_top-10-under-10-beauty-1429569507.jpg");
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 7;
        nuevaCategoria = new Categoria(id, "Juguetes", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHOUYzvSZB--LBO3F6rosUKRNVssrms22xt9MyLmdPw7LIdCOGuA"
        );
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 8;
        nuevaCategoria = new Categoria(id, "Artesanías", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ--EBikLFTN3RyIgl7Abor7Px0H6zMOFP6Mw832Nb2fF-CM0Q5"
        );
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);
        id = 9;
        nuevaCategoria = new Categoria(id, "Tecnología", "http://e00-elmundo.uecdn.es/p/108/sp/10800/thumbnail/entry_id/0_gluyz6bz/version/100001/height/480"
        );
        myRef = database.getReference("Categorias/" + id + "/");
        myRef.setValue(nuevaCategoria);

    }

}
