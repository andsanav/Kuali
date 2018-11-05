package mx.itesm.kuali.kuali;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formulario_Producto extends AppCompatActivity {

    EditText nombre_producto;
    EditText marca;
    EditText tiendas;
    EditText precio;
    EditText imagenes;
    EditText redes_sociales;

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
        imagenes = findViewById(R.id.url_imagen);
        redes_sociales = findViewById(R.id.sociales);
    }

    public void validar_informacion(View v) {
        boolean valid = true;
        String[] urls;
        while(true) {
            if (nombre_producto.getText().toString().trim().length() <= 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error en los datos");
                builder.setMessage("Nombre del Producto Vacío");
                builder.setPositiveButton("Aceptar", null);
                builder.show();
                break;
            }

            urls = imagenes.getText().toString().trim().split(",");
            Log.i("URLS", urls[0]);
            for (String url : urls) {
                if (!url_valido(url)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Error en los datos");
                    builder.setMessage("URL mal formado en las imágenes");
                    builder.setPositiveButton("Aceptar", null);
                    builder.show();
                    valid = false;
                    break;
                }
            }


            if(!valid)
                break;

            if (marca.getText().toString().trim().length() <= 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error en los datos");
                builder.setMessage("Nombre de la Marca Vacía");
                builder.setPositiveButton("Aceptar", null);
                builder.show();
                break;
            }

            Log.i("URL", tiendas.getText().toString().trim().length()+"");
            if(tiendas.getText().toString().trim().length() > 0) {
                urls = tiendas.getText().toString().trim().split(",");
                for (String url : urls) {
                    if (!url_valido(url)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Error en los datos");
                        builder.setMessage("URL mal formado en las tiendas");
                        builder.setPositiveButton("Aceptar", null);
                        builder.show();
                        valid = false;
                        break;
                    }
                }
            }

            if(!valid)
                break;

            if(redes_sociales.getText().toString().trim().length() > 0) {
                urls = redes_sociales.getText().toString().trim().split(",");
                for (String url : urls) {
                    if (!url_valido(url)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Error en los datos");
                        builder.setMessage("URL mal formado en las redes sociales");
                        builder.setPositiveButton("Aceptar", null);
                        builder.show();
                        valid = false;
                        break;
                    }
                }
            }

            if (precio.getText().toString().equals("") || Double.parseDouble(precio.getText().toString()) <= 0.0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error en los datos");
                builder.setMessage("Precio Inválido");
                builder.setPositiveButton("Aceptar", null);
                builder.show();
                break;
            }

            if(!valid)
                break;

            if (valid) {
                enviar_informacion();
            }
        }
    }

    private void enviar_informacion(){
        Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
        itSend.setType("plain/text");
        String message = "";
        message += "Nombre Producto: " + nombre_producto.getText().toString() + "\n";
        message += "Imagenes Producto: " + imagenes.getText().toString() + "\n";
        message += "Marca: " + marca.getText().toString() + "\n";
        message += "Redes Sociales: " + redes_sociales.getText().toString() + "\n";
        message += "Tiendas: " + tiendas.getText().toString() + "\n";
        message += "Precio: " + precio.getText().toString();

        itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "app.kuali.mx@gmail.com"});
        itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, "Nuevo Producto");
        itSend.putExtra(android.content.Intent.EXTRA_TEXT, message);

        startActivity(itSend);

    }

    private boolean url_valido(String url) {
        String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        try {
            Pattern patt = Pattern.compile(regex);
            Matcher matcher = patt.matcher(url);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }

}
