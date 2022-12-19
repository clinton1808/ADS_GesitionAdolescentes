package pe.unjfsc.ads.java8.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class conexion {
     Connection conexion = null;
     String HOST = "localhost";
     String PUERTO = "5432";
     String DB = "Gestion__Adolescentes";
     String USER = "postgres";
     String PASSWORD = "12345678";   
     
     String url ="jdbc:postgresql://"+HOST+":"+PUERTO+"/"+DB;
    
    public Connection getConexion(){
        
        
        try {
            Class.forName("org.postgresql.Driver");
            
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
           // JOptionPane.showMessageDialog(null, "conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return conexion;
    }
}
 

