package mx.itesm.kuali.kuali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PantallaInicio extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Productos");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        cargarRegistros(myRef);
        leerPreferencias();
    }

    private void cargarRegistros(DatabaseReference myRef){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setMax((int)dataSnapshot.getChildrenCount());
                int count = 0;
                Producto producto;
                Producto.lista_productos.clear();
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
                    producto = new Producto(id, nombre, precio_promedio, marca, url_marca_logo, url_imagenes, categorias,
                            url_tiendas, url_sociales, descripcion, likes);
                    Producto.lista_productos.add(producto);
                    count++;
                    progressBar.setProgress(count);

                }
                Log.i("Cambio", "Terminó de cargar");
                Intent tabMain = new Intent(getBaseContext(), TabMain.class);
                startActivity(tabMain);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Error:", error.toException());
            }
        });
    }

    private void leerPreferencias(){
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Set<String> favoritos = sharedPref.getStringSet("favoritos", null);
        if(favoritos == null){
            Categoria.elementos_likes = new HashSet<String>();
            /*favoritos.add("1");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putStringSet("favoritos", favoritos);
            editor.apply();
            Log.i("Favoritos", "Agregados");*/
        } else {
            Categoria.elementos_likes = (HashSet<String>) favoritos;
            Log.i("Favoritos", favoritos.toString());
        }
    }

    @Override
    protected void onStop() {
        guardarPreferencias();
        super.onStop();
    }

    private void guardarPreferencias(){
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("favoritos");
        editor.putStringSet("favoritos", Categoria.elementos_likes);
        Log.i("Editor", editor.commit()+"");
        Log.i("Salvando", Categoria.elementos_likes.toString());
    }
}
