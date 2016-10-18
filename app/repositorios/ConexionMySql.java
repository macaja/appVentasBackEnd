package repositorios;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by mauricio on 18/10/16.
 */
public class ConexionMySql {
    public Connection obtenerConexion() throws  Exception{
        String driver = "com.mysql.jdbc.Driver";
        String connection = "jdbc:mysql://localhost:3306/appVentas";
        String user = "root";
        String password = "root";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(connection,user,password);
        return con;
    }
}
