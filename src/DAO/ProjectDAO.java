/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import DTO.Project;
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
public class ProjectDAO {
     private static final String SQL_READ = "SELECT id, name, username, "
            + "password, role_id FROM user WHERE id = ?";
        private static final String SQL_READALL = "SELECT id, name FROM project";        
        private static final String SQL_CREATE = "INSERT INTO project (name) VALUES (?)";
        private static final String SQL_DELETE = "DELETE FROM project where id = ?";
        
        private static final Connector con = Connector.getConnection();
        
         public Project read(Object key) {
         PreparedStatement ps;
         Project dto = null;
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               dto = new Project(rs.getInt(1),rs.getString(2));
            }
            return dto; 
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return dto;
    }
    
        public ArrayList<Project> readAll() {
         PreparedStatement ps;
         Project dto = null;
         ArrayList<Project> arr = new ArrayList<Project>();
        try {

            ps = con.getConexion().prepareStatement(SQL_READALL);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               dto = new Project(rs.getInt(1),rs.getString(2));
               arr.add(dto);
            }
            return arr; 
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return arr;
    }
    
   
        
         public boolean create(Project project) {
         PreparedStatement ps;
         String p_name = null;
        try {
            String sqlcreate = SQL_CREATE;
            ps = con.getConexion().prepareStatement(sqlcreate);
            p_name = project.getName();
            ps.setString(1, p_name);
            
            if(ps.executeUpdate()>0){
           
            return true; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return false;
    }
         
         public boolean delete(Project project) {
         PreparedStatement ps;
         int p_id = 0;
        try {
            String sqldelete = SQL_DELETE;
            ps = con.getConexion().prepareStatement(sqldelete);
            p_id = project.getId();
            ps.setInt(1, p_id);
            
            if(ps.executeUpdate()>0){
           
            return true; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.desconectar();
        }
        return false;
    }
}
