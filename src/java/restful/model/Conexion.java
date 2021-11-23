/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.model;

import java.sql.*;

/**
 *
 * @author nian
 */
public class Conexion {
    
     public static Connection getConnection(){
         
         try {
             Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/C3_GR3", "root", "8203");
            //PreparedStatement pst = cn.prepareStatement("insert into alumnos values(?,?,?)");
             System.out.println("Conexion establecida con exito");
             return cn;
         } catch (SQLException e) {
             System.out.println("Error de conexion"+e);
             return null;
         }
     }
    
    
}
