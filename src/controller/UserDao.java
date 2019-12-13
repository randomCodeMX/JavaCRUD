/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author josaf
 */
public class UserDao extends Connect{
    private Connection conn = getConnection();    
    
    public boolean login(User user){
        boolean b = false;
        try{
            String sql ="Select * from users where userName='" + user.getUserName() + "' and password='" + user.getPassword() + " ' ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                b= true;
            }
        }catch(SQLException e){            
            System.out.println(e.getSQLState());            
        }
        return b;
        
    }
    
    public boolean insertUser(User user){
        try {
            CallableStatement cs = conn.prepareCall("{call sp_insertUser(?,?,?,?,?,?)}");
            cs.setString(1, user.getUserName());
            cs.setString(2, user.getName());
            cs.setString(3, user.getLastName());
            cs.setString(4, user.getPhoneNumber());
            cs.setString(5, user.getEmail());
            cs.setString(6, user.getPassword());            
            cs.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }  
    }
    
    public ArrayList<User> listUser(){
        ArrayList listUsers = new ArrayList();
        User user;
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from users");
            ResultSet rs = ps.executeQuery();
            /*                  Username       Name           LastName        Email           PhoneNumber     Password  */
            while(rs.next()){
                user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        return listUsers;
    }
    
    public boolean deleteUser(User user){
        try {
            String sql = "Delete from users where userName='" + user.getUserName() + "'";            
            Statement st = conn.createStatement();
            if (st.executeUpdate(sql) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
    
    
}
