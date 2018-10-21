package mx.itesm.kuali.kuali;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Andrea on 10/11/18.
 */

public class Producto {

    public Integer id;
    public String nombre;
    public Integer numLikes;
    public double precio;
    String marca;
    String url_marca_logo;
    ArrayList<String> url_imagenes;
    ArrayList<CATEGORIAS> categorias;
    ArrayList<ArrayList<String>> url_tiendas;
    ArrayList<ArrayList<String>> url_sociales;
    String descripcion;

    public Producto() {
    }

    public Producto(Integer id, String nombre, Double precio, String marca, String url_marca_logo, ArrayList<String> url_imagenes, ArrayList<CATEGORIAS> categorias, ArrayList<ArrayList<String>> url_tiendas, ArrayList<ArrayList<String>> url_sociales, String descripcion, Integer likes) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.url_marca_logo = url_marca_logo;
        this.url_imagenes = url_imagenes;
        this.categorias = categorias;
        this.url_tiendas = url_tiendas;
        this.url_sociales = url_sociales;
        this.descripcion = descripcion;
        this.numLikes = likes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public double getPrecio() {
        return precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUrl_marca_logo() {
        return url_marca_logo;
    }

    public void setUrl_marca_logo(String url_marca_logo) {
        this.url_marca_logo = url_marca_logo;
    }

    public ArrayList<CATEGORIAS> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<CATEGORIAS> categorias) {
        this.categorias = categorias;
    }

    public ArrayList<ArrayList<String>> getUrl_tiendas() {
        return url_tiendas;
    }

    public void setUrl_tiendas(ArrayList<ArrayList<String>> url_tiendas) {
        this.url_tiendas = url_tiendas;
    }

    public ArrayList<ArrayList<String>> getUrl_sociales() {
        return url_sociales;
    }

    public void setUrl_sociales(ArrayList<ArrayList<String>> url_sociales) {
        this.url_sociales = url_sociales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<String> getUrl_imagenes() {
        return url_imagenes;
    }

    public void setUrl_imagenes(ArrayList<String> url_imagenes) {this.url_imagenes = url_imagenes;
    }
}
