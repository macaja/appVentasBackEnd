package repositorios;

import models.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauricio on 18/10/16.
 */
public class ArticuloRepositorio {
    public void agregarArticulo(Articulo articulo){
        String nombre = articulo.getNombre();
        Integer precio = articulo.getPrecio();
        String descripcion = articulo.getDescripcion();
        String imagen = articulo.getImagen();
        Integer cantidad = articulo.getCantidad();
        Integer categoria = articulo.getCategoria();
        String query = "INSERT INTO `appVentas`.`articulo` (`art_nombre`, `art_precio`, `art_descripcion`, `art_imagen`, `art_cantidad`, `cat_id`) VALUES (?,?,?,?,?,?);";
        PreparedStatement ps= null;
        Connection con = null;
        try {
            con = new ConexionMySql().obtenerConexion();
            ps = con.prepareStatement(query);
            ps.setString(1,nombre);
            ps.setInt(2,precio);
            ps.setString(3,descripcion);
            ps.setString(4,imagen);
            ps.setInt(5,cantidad);
            ps.setInt(6,categoria);
            ps.execute();
            con.close();
        }catch (Exception e){
            System.err.println("Exception al ingresar articulo a la Base de Datos!");
            System.err.println(e.getMessage());
        }
    }
    public ArrayList<Articulo> obtenerArticulos(){
        ArrayList<Articulo> articulos = new ArrayList<>();
        String query = "SELECT art_id,art_nombre,art_precio,art_descripcion,art_imagen,art_cantidad,cat_id FROM articulo;";
        Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            con = new ConexionMySql().obtenerConexion();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Integer id = rs.getInt("art_id");
                String nombre = rs.getString("art_nombre");
                Integer precio = rs.getInt("art_precio");
                String descripcion = rs.getString("art_descripcion");
                String imagen = rs.getString("art_imagen");
                Integer cantidad = rs.getInt("art_cantidad");
                Integer categoria = rs.getInt("cat_id");
                Articulo articulo = new Articulo(id,nombre,precio,descripcion,imagen,cantidad,categoria);
                articulos.add(articulo);
            }
            con.close();
        }catch (Exception e){
            System.err.println("Exception al obtener Articulos de la Base de Datos!");
            System.err.println(e.getMessage());
        }
        return articulos;
    }
}
