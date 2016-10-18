package models;


/**
 * Created by mauricio on 18/10/16.
 */

public class Articulo {
    public Integer id;
    public String  nombre;

    public Articulo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Articulo(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
