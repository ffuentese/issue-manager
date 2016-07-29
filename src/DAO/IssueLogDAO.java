/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Issue;
import DTO.IssueLog;
import DTO.Project;
import DTO.Status;
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
public class IssueLogDAO {

    private static final String SQL_READ = "SELECT id, description, "
            + "issue_id, status_id, date, asignee_id, priority "
            + "FROM issuelog WHERE id = ?";
    private static final String SQL_CREATE = "INSERT INTO issuelog "
            + "(description, issue_id, status_id, date, asignee_id, priority) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_READALL = "SELECT id, description, "
            + "issue_id, status_id, date, asignee_id, priority "
            + "FROM issuelog";    
    private static final String SQL_READISSUE = "SELECT id, description, "
            + "issue_id, status_id, date, asignee_id, priority "
            + "FROM issuelog WHERE issue_id = ?";    
    private static final String SQL_READONEISSUE = "SELECT id, description, "
            + "issue_id, status_id, date, asignee_id, priority "
            + "FROM issuelog WHERE issue_id = ? "
            + "ORDER BY id DESC "
            + "LIMIT 1";        

    private static final Connector con = Connector.getConnection();

    public IssueLogDAO() {
    }

    public boolean create(IssueLog issuelog) {
        PreparedStatement ps;
        String p_desc;
        User p_asignee;
        Status p_status;
        Issue p_issue;
        Date p_date;
        int p_priority;
        try {
            String sqlcreate = SQL_CREATE;
            ps = con.getConexion().prepareStatement(sqlcreate);
            p_desc = issuelog.getDescription();
            p_issue = issuelog.getIssue();
            p_status = issuelog.getStatus();
            p_date = issuelog.getDate();
            p_asignee = issuelog.getAsignee();
            p_priority = issuelog.getPriority();

            ps.setString(1, p_desc);
            ps.setInt(2, p_issue.getId());
            ps.setInt(3, p_status.getId());
            ps.setDate(4, new java.sql.Date(p_date.getTime()));
            ps.setInt(5, p_asignee.getId());
            ps.setInt(6, p_priority);

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

    public IssueLog read(Object key) {
        PreparedStatement ps;
        IssueLog dto = null;
        IssueDAO issuedao = new IssueDAO();
        StatusDAO statusdao = new StatusDAO();
        UserDAO userdao = new UserDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new IssueLog(rs.getInt(1), rs.getString(2),
                        issuedao.read(rs.getInt(3)), statusdao.read(rs.getInt(4)),
                        rs.getDate(5), userdao.read(rs.getInt(6)), rs.getInt(7));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return dto;
    }
    
    public ArrayList<IssueLog> readAll() {
        PreparedStatement ps;
        IssueLog dto = null;
        IssueDAO issuedao = new IssueDAO();
        StatusDAO statusdao = new StatusDAO();
        UserDAO userdao = new UserDAO();
        ArrayList<IssueLog> arr = new ArrayList<IssueLog>();
        try {

            ps = con.getConexion().prepareStatement(SQL_READALL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new IssueLog(rs.getInt(1), rs.getString(2),
                        issuedao.read(rs.getInt(3)), statusdao.read(rs.getInt(4)),
                        rs.getDate(5), userdao.read(rs.getInt(6)), rs.getInt(7));
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
    
    public ArrayList<IssueLog> readAllfromIssue(Object key) {
        PreparedStatement ps;
        IssueLog dto = null;
        IssueDAO issuedao = new IssueDAO();
        StatusDAO statusdao = new StatusDAO();
        UserDAO userdao = new UserDAO();
        ArrayList<IssueLog> arr = new ArrayList<IssueLog>();
        try {

            ps = con.getConexion().prepareStatement(SQL_READISSUE);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new IssueLog(rs.getInt(1), rs.getString(2),
                        issuedao.read(rs.getInt(3)), statusdao.read(rs.getInt(4)),
                        rs.getDate(5), userdao.read(rs.getInt(6)), rs.getInt(7));
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
    
    public IssueLog readOnefromIssue(Object key) {
        PreparedStatement ps;
        IssueLog dto = null;
        IssueDAO issuedao = new IssueDAO();
        StatusDAO statusdao = new StatusDAO();
        UserDAO userdao = new UserDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READONEISSUE);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new IssueLog(rs.getInt(1), rs.getString(2),
                        issuedao.read(rs.getInt(3)), statusdao.read(rs.getInt(4)),
                        rs.getDate(5), userdao.read(rs.getInt(6)), rs.getInt(7));
                
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return dto;
    }
}
