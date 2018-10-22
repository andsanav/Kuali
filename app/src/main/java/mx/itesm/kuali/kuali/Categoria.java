package mx.itesm.kuali.kuali;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Andrea on 10/19/18.
 */

public class Categoria {

    public Integer id;
    public String nombre;
    public String url_thumbnail;

    public static ArrayList<Categoria> lista_categorias = new ArrayList<>();
    public static int categoria_busqueda;
    public static String elemento_busqueda;
    public static ArrayList<Integer> elementos_likes = new ArrayList<>();

    public Categoria(Integer id, String nombre, String url_thumbnail) {
        this.id = id;
        this.nombre = nombre;
        this.url_thumbnail = url_thumbnail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl_thumbnail() {
        return url_thumbnail;
    }

    public void setUrl_thumbnail(String thumbnail) {
        this.url_thumbnail = thumbnail;
    }
}
