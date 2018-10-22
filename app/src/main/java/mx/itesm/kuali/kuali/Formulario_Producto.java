package mx.itesm.kuali.kuali;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Formulario_Producto extends AppCompatActivity {

    EditText nombre_producto;
    EditText marca;
    EditText tiendas;
    EditText precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__producto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nombre_producto = findViewById(R.id.nombre_producto);
        marca = findViewById(R.id.marca);
        tiendas = findViewById(R.id.tiendas);
        precio = findViewById(R.id.precio);
    }

    public void enviar_informacion(View v){
        Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
        itSend.setType("plain/text");
        String message = "";
        message += "Nombre Producto: " + nombre_producto.getText().toString() + "\n";
        message += "Marca: " + marca.getText().toString() + "\n";
        message += "Tiendas: " + tiendas.getText().toString() + "\n";
        message += "Precio: " + precio.getText().toString();

        itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "app.kuali.mx@gmail.com"});
        itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, "Nuevo Producto");
        itSend.putExtra(android.content.Intent.EXTRA_TEXT, message);

        startActivity(itSend);

    }

}
