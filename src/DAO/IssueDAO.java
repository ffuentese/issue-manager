/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Issue;
import DTO.User;
import conn.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco
 */
public class IssueDAO {

    private static final String SQL_READ = "SELECT id, status_id, creator_id, "
            + "project_id, description, date, "
            + "assignee_id, owner_id, priority FROM issue WHERE id = ?";
    private static final String SQL_READAUTH = "SELECT id, name, username, "
            + "password, role_id FROM issue WHERE username = ?";
    private static final String SQL_READALL = "SELECT id, status_id, creator_id, "
            + "project_id, description, date, "
            + "assignee_id, owner_id, priority FROM issue";
    private static final String SQL_CREATE = "INSERT INTO issue (name, username, "
            + "password, role_id) VALUES (?, ?, ?, ?)";

    private static final Connector con = Connector.getConnection();

    public Issue read(Object key) {
        PreparedStatement ps;
        Issue dto = null;
        RoleDAO role = new RoleDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
        //        dto = new Issue(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), role.read(rs.getInt(5)));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(Issue.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return dto;
    }
}
