/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    
    public static Connection conn ;
    public static final String driver="com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String pass = "";
    public static final String url = "jdbc:mysql://localhost/store";
    
    public Connect(){
        conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            System.out.println("Connection started");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void closeConnnection() throws SQLException{
        conn.close();
    }
    
    public Connection getConnection(){
        return conn;
    }
    
            
            
    
    
    
}
