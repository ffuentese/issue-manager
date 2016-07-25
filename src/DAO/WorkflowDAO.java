/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Workflow;
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
public class WorkflowDAO {

    private static final String SQL_READ = "SELECT id, name, "
            + "description FROM workflow WHERE id = ?";
    private static final String SQL_READALL = "SELECT id, name, description FROM workflow";
    private static final String SQL_CREATE = "INSERT INTO workflow (name, description) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM workflow where id = ?";

    private static final Connector con = Connector.getConnection();

    public Workflow read(Object key) {
        PreparedStatement ps;
        Workflow dto = null;
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new Workflow(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(Workflow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return dto;
    }

    public ArrayList<Workflow> readAll() {
        PreparedStatement ps;
        Workflow dto = null;
        ArrayList<Workflow> arr = new ArrayList<Workflow>();
        try {

            ps = con.getConexion().prepareStatement(SQL_READALL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new Workflow(rs.getInt(1), rs.getString(2), rs.getString(3));
                arr.add(dto);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(Workflow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return arr;
    }

    public boolean create(Workflow workflow) {
        PreparedStatement ps;
        String p_name, p_desc;
        try {
            String sqlcreate = SQL_CREATE;
            ps = con.getConexion().prepareStatement(sqlcreate);
            p_name = workflow.getName();
            p_desc = workflow.getDescription();
            ps.setString(1, p_name);
            ps.setString(2, p_desc);
            
            if (ps.executeUpdate() > 0) {

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Workflow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return false;
    }

    public boolean delete(Workflow project) {
        PreparedStatement ps;
        int p_id = 0;
        try {
            String sqldelete = SQL_DELETE;
            ps = con.getConexion().prepareStatement(sqldelete);
            p_id = project.getId();
            ps.setInt(1, p_id);

            if (ps.executeUpdate() > 0) {

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Workflow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return false;
    }
}
