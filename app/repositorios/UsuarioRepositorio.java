package repositorios;

import models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by mauricio on 21/10/16.
 */
public class UsuarioRepositorio {
    public boolean registrarUsuario(Usuario usuario){
        String username = usuario.getUsername();
        String password = usuario.getPassword();
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
        boolean registrado = false;
        boolean usuarioExiste = usuarioRepositorio.validarUsuarioExiste(usuario);
        if(usuarioExiste == false){
            String query = "INSERT INTO usuario (usu_username,usu_password) VALUES(?,?)";
            PreparedStatement ps = null;
            Connection con = null;
            try{
                con = new ConexionMySql().obtenerConexion();
                ps = con.prepareStatement(query);
                ps.setString(1,username);
                ps.setString(2,password);
                ps.execute();
                con.close();
                registrado = true;
            }catch (Exception e){
                System.err.println("Exception al ingresar usuario en la BD");
                System.err.println(e.getMessage());
            }
        }
        return registrado;

    }
    public boolean validarUsuarioExiste(Usuario usuario){
        String query = "SELECT usu_username FROM usuario";
        Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        boolean existe = false;
        try {
            con = new ConexionMySql().obtenerConexion();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                String username = rs.getString("usu_username");
                if(username.equalsIgnoreCase(usuario.getUsername())){
                    existe = true;
                    break;
                }else{
                    continue;
                }
            }
        }catch (Exception e){
            System.err.println("Exception al consultar validarUsuarioExiste en la BD");
            System.err.println(e.getMessage());
        }
        return  existe;
    }
}
