package mx.itesm.kuali.kuali;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

public class BusquedaProductos extends AppCompatActivity {

    TextView titulo;
    SearchView busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_productos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        titulo = findViewById(R.id.titulo_busqueda);
        busqueda = findViewById(R.id.busqueda_busqueda);
        titulo.setText("Coincidencias para: \'" + Categoria.elemento_busqueda +"\'");
        busqueda.setQueryHint("Buscar");
        busqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Categoria.elemento_busqueda = query;
                Intent tabMain = new Intent(getBaseContext(), BusquedaProductos.class);
                startActivity(tabMain);
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
