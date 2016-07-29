/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import conn.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
import DTO.User;
import java.util.ArrayList;
public class UserDAO {
        private static final String SQL_READ = "SELECT id, name, username, "
            + "password, role_id FROM user WHERE id = ?";
        private static final String SQL_READAUTH = "SELECT id, name, username, "
            + "password, role_id FROM user WHERE username = ?";
        private static final String SQL_READALL = "SELECT id, name, username, "
            + "password, role_id FROM user";        
        private static final String SQL_CREATE = "INSERT INTO user (name, username, "
                +"password, role_id) VALUES (?, ?, ?, ?)";
        private static final String SQL_HASH = "SELECT hash from hash where user_id = ?";
        
        private static final Connector con = Connector.getConnection();
        
         public User read(Object key) {
         PreparedStatement ps;
         User dto = null;
         RoleDAO role = new RoleDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               dto = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),role.read(rs.getInt(5)));
            }
            return dto; 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return dto;
    }
         
         public ArrayList<User> readAll() {
         PreparedStatement ps;
         User dto = null;
         RoleDAO role = new RoleDAO();
         ArrayList<User> arr = new ArrayList<User>();
        try {
            ps = con.getConexion().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               dto = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),role.read(rs.getInt(5)));
               arr.add(dto);
            }
            return arr; 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return arr;
    }
    
        
    
        public User readAuth(Object key) {
         PreparedStatement ps;
         User dto = null;
         RoleDAO role = new RoleDAO();
        try {
            String sqlauth = SQL_READAUTH;
            ps = con.getConexion().prepareStatement(sqlauth);
            ps.setString(1, key.toString());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               dto = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),role.read(rs.getInt(5)));
            }
            return dto; 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return dto;
    }
        
         public String hash(int user_id) {
         PreparedStatement ps;
         String hash = null;
        try {
            String sqlauth = SQL_HASH;
            ps = con.getConexion().prepareStatement(sqlauth);
            
            ps.setInt(1, user_id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               hash = rs.getString(1);
            }
            return hash; 
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return hash;
    }
}
