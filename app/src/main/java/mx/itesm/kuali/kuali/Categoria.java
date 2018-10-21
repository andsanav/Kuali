package mx.itesm.kuali.kuali;

import android.graphics.Bitmap;

/**
 * Created by Andrea on 10/19/18.
 */

public class Categoria {

    public Integer id;
    public String nombre;
    public String url_thumbnail;

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
