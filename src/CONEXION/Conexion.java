/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONEXION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Conexion {
    public static Connection conector(){
        String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String URL="jdbc:sqlserver://MSI:1433;databaseName=Compilador";
        Connection con = null;
        try{
            //Class.forName(Driver);
            con=DriverManager.getConnection(URL,"sa","123456789");
            //System.out.println("Conectado");
        }catch(Exception e){
            System.out.println("Error:"+e.getMessage());
        }
        return con;
    }
}
