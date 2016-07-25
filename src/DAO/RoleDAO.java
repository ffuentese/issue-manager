/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import DTO.Role;
import DTO.User;
import conn.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class RoleDAO {
    private static final String SQL_READ = "SELECT id, name FROM role WHERE id = ?";
    private static final String SQL_READALL = "SELECT id, name FROM role ";
    
    private static final Connector con = Connector.getConnection();
        
         public Role read(Object key) {
         PreparedStatement ps;
         Role dto = null;
         RoleDAO role = new RoleDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               dto = new Role(rs.getInt(1),rs.getString(2));
            }
            return dto; 
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return dto;
    }
         
         public ArrayList<Role> readAll() {
         PreparedStatement ps;
         Role dto = null;
         ArrayList<Role> arr = new ArrayList<Role>();
        try {

            ps = con.getConexion().prepareStatement(SQL_READALL);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               dto = new Role(rs.getInt(1),rs.getString(2));
               arr.add(dto);
            }
            return arr; 
        } catch (SQLException ex) {
            Logger.getLogger(Role.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return arr;
    }
}
