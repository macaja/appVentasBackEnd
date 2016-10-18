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
        String query = "INSERT INTO articulos (`art_nombre`) VALUES (?);";
        PreparedStatement ps= null;
        Connection con = null;
        try {
            con = new ConexionMySql().obtenerConexion();
            ps = con.prepareStatement(query);
            ps.setString(1,nombre);
            ps.execute();
            con.close();
        }catch (Exception e){
            System.err.println("Exception al ingresar articulo a la Base de Datos!");
            System.err.println(e.getMessage());
        }
    }
    public ArrayList<Articulo> obtenerArticulos(){
        ArrayList<Articulo> articulos = new ArrayList<>();
        String query = "SELECT art_id,art_nombre FROM articulos";
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
                Articulo articulo = new Articulo(id,nombre);
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
