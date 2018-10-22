package mx.itesm.kuali.kuali;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CategoriasActivity extends AppCompatActivity {

    TextView nombre_categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nombre_categoria = (TextView) findViewById(R.id.nombre_categoria);
        mostrar_nombre_categoria();
    }

    private void mostrar_nombre_categoria(){
        switch(Categoria.categoria_busqueda){
            case 1:
                nombre_categoria.setText("Mascotas"); break;
            case 2:
                nombre_categoria.setText("Alimentos"); break;
            case 3:
                nombre_categoria.setText("Hogar"); break;
            case 4:
                nombre_categoria.setText("Cosméticos"); break;
            case 5:
                nombre_categoria.setText("Ropa y accesorios"); break;
            case 6:
                nombre_categoria.setText("Salud y Belleza"); break;
            case 7:
                nombre_categoria.setText("Juguetes"); break;
            case 8:
                nombre_categoria.setText("Artesanías"); break;
            case 9:
                nombre_categoria.setText("Tecnología"); break;
        }
    }

}
