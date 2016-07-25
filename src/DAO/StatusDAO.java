/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Status;
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
public class StatusDAO {

    private static final String SQL_READ = "SELECT id, name, "
            + "workflow_id FROM status WHERE id = ?";
    private static final String SQL_READALL = "SELECT id, name, workflow_id FROM status";
    private static final String SQL_READWORKFLOW = "SELECT id, name, workflow_id FROM"
            + " status where workflow_id = ?";
    private static final String SQL_CREATE = "INSERT INTO status (name, workflow_id) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM status where id = ?";

    private static final Connector con = Connector.getConnection();

    public Status read(Object key) {
        PreparedStatement ps;
        Status dto = null;
        WorkflowDAO workflow = new WorkflowDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new Status(rs.getInt(1), rs.getString(2), workflow.read(rs.getInt(3)));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(Workflow.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return dto;
    }

    public ArrayList<Status> readAll() {
        PreparedStatement ps;
        Status dto = null;
        ArrayList<Status> arr = new ArrayList<Status>();
        WorkflowDAO workflow = new WorkflowDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READALL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new Status(rs.getInt(1), rs.getString(2), workflow.read(rs.getInt(3)));

                arr.add(dto);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return arr;
    }

    public ArrayList<Status> readAllWorkflow(Object key) {
        PreparedStatement ps;
        Status dto = null;
        ArrayList<Status> arr = new ArrayList<Status>();
        WorkflowDAO workflow = new WorkflowDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READWORKFLOW);
            ps.setInt(1, Integer.parseInt(key.toString()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new Status(rs.getInt(1), rs.getString(2), workflow.read(rs.getInt(3)));

                arr.add(dto);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(Status.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return arr;
    }

    public boolean create(Status status) {
        PreparedStatement ps;
        String p_name;
        Workflow p_work;
        try {
            String sqlcreate = SQL_CREATE;
            ps = con.getConexion().prepareStatement(sqlcreate);
            p_name = status.getName();
            p_work = status.getWorkflow();
            ps.setString(1, p_name);
            ps.setInt(2, p_work.getId());

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
