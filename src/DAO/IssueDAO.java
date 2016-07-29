/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Issue;
import DTO.Project;
import DTO.User;
import conn.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class IssueDAO {

    private static final String SQL_READ = "SELECT id, name, creator_id, "
            + "project_id, date, owner_id FROM issue WHERE id = ?";
    private static final String SQL_READALL = "SELECT id, name, creator_id, "
            + "project_id, date, owner_id FROM issue ";
    private static final String SQL_CREATE = "INSERT INTO issue (name, creator_id, "
            + "project_id, date, owner_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_CHECK = "SELECT id  FROM issue WHERE name = ?"
            + " AND creator_id = ? AND project_id = ? AND date = ? AND owner_id = ?";
    private static final String SQL_ISSUEMAN = "SELECT a.id, a.name, a.project_id, b.status_id, b.asignee_id "
            + " FROM issue a"
            + " JOIN issuelog b on a.id=b.issue_id ";

    private static final Connector con = Connector.getConnection();

    public boolean create(Issue issue) {
        PreparedStatement ps;
        String p_name;
        User p_creator, p_owner;
        Project p_proj;
        Date p_date;
        try {
            String sqlcreate = SQL_CREATE;
            ps = con.getConexion().prepareStatement(sqlcreate);
            p_name = issue.getName();
            p_creator = issue.getCreator();
            p_proj = issue.getProject();
            p_date = issue.getDate();
            p_owner = issue.getOwner();
            ps.setString(1, p_name);
            ps.setInt(2, p_creator.getId());
            ps.setInt(3, p_proj.getId());
            ps.setDate(4, new java.sql.Date(p_date.getTime()));
            ps.setInt(5, p_owner.getId());

            if (ps.executeUpdate() > 0) {

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return false;
    }

    public Issue read(Object key) {
        PreparedStatement ps;
        Issue dto = null;
        UserDAO userdao = new UserDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new Issue(rs.getInt(1), rs.getString(2), userdao.read(rs.getInt(3)), projectDAO.read(rs.getInt(4)), rs.getDate(5), userdao.read(rs.getInt(6)));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return dto;
    }

    public int check(Issue issue) {
        PreparedStatement ps;
        int val = 0;
        UserDAO userdao = new UserDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_CHECK);
            ps.setString(1, issue.getName());
            ps.setInt(2, issue.getCreator().getId());
            ps.setInt(3, issue.getProject().getId());
            ps.setDate(4, new java.sql.Date(issue.getDate().getTime()));
            ps.setInt(5, issue.getOwner().getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                val = rs.getInt(1);
            }
            return val;
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return val;
    }

    public ArrayList<Issue> readAll() {
        PreparedStatement ps;
        Issue dto = null;
        RoleDAO role = new RoleDAO();
        UserDAO userdao = new UserDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        ArrayList<Issue> arr = new ArrayList<Issue>();
        try {

            ps = con.getConexion().prepareStatement(SQL_READALL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new Issue(rs.getInt(1), rs.getString(2), userdao.read(rs.getInt(3)), projectDAO.read(rs.getInt(4)), rs.getDate(5), userdao.read(rs.getInt(6)));
                arr.add(dto);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return arr;
    }
    
//    public Issue readIssueMan(Object key) {
//        PreparedStatement ps;
//        Issue dto = null;
//        UserDAO userdao = new UserDAO();
//        ProjectDAO projectDAO = new ProjectDAO();
//        try {
//
//            ps = con.getConexion().prepareStatement(SQL_READ);
//            ps.setInt(1, Integer.parseInt(key.toString()));
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                dto = new Issue(rs.getInt(1), rs.getString(2), userdao.read(rs.getInt(3)), projectDAO.read(rs.getInt(4)), rs.getDate(5), userdao.read(rs.getInt(6)));
//            }
//            return dto;
//        } catch (SQLException ex) {
//            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            con.desconectar();
//        }
//        return dto;
//    }
}
