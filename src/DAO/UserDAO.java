/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Role;
import DTO.Status;
import conn.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Francisco
 */
import DTO.User;
import DTO.Workflow;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class UserDAO {

    private static final String SQL_READ = "SELECT id, name, username, "
            + "password, role_id FROM user WHERE id = ?";
    private static final String SQL_READAUTH = "SELECT id, name, username, "
            + "password, role_id FROM user WHERE username = ?";
    private static final String SQL_READALL = "SELECT id, name, username, "
            + "password, role_id FROM user";
    private static final String SQL_CREATE = "INSERT INTO user (name, username, "
            + "password, role_id) VALUES (?, ?, ?, ?)";
    private static final String SQL_HASH = "SELECT hash from hash where user_id = ?";
    private static final String SQL_CREATEHASH = "INSERT INTO hash (user_id, hash) "
            + " VALUES (?, ?)";
    private static final String SQL_UPDATEHASH = "UPDATE hash SET hash = ? where user_id = ?";
    private static final String LAST_ID = "SELECT max(id)+1 FROM user group by id order by id desc limit 1;";
    private static final String SQL_UPDATE = "UPDATE user set name = ?, username = ?, password = ?, role_id = ?"
            + " WHERE id = ?";

    private static final Connector con = Connector.getConnection();

    public User read(Object key) {
        PreparedStatement ps;
        User dto = null;
        RoleDAO role = new RoleDAO();
        try {

            ps = con.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, Integer.parseInt(key.toString()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dto = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), role.read(rs.getInt(5)));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
            while (rs.next()) {
                dto = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), role.read(rs.getInt(5)));
                arr.add(dto);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
            while (rs.next()) {
                dto = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), role.read(rs.getInt(5)));
            }
            return dto;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
            while (rs.next()) {
                hash = rs.getString(1);
            }
            return hash;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return hash;
    }

    public boolean create(User user) {
        PreparedStatement ps;
        int id;
        String p_name;
        String p_username;
        String p_password;
        Role p_role;
        try {
            String sqlcreate = SQL_CREATE;
            ps = con.getConexion().prepareStatement(sqlcreate);
            p_name = user.getName();
            p_username = user.getUsername();

            if (this.hashCreate(this.last_id())) {
                p_password = user.encrypt(user.getPassword() + this.hash(this.last_id()));
                p_role = user.getRole();
                ps.setString(1, p_name);
                ps.setString(2, p_username);
                ps.setString(3, p_password);
                ps.setInt(4, p_role.getId());

                if (ps.executeUpdate() > 0) {

                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return false;
    }

    public boolean hashCreate(int last_id) {
        PreparedStatement ps;
        Random r = new Random();
        String p_hash = Long.toString(r.nextLong(), 36);
        try {
            String sqlcreate = SQL_CREATEHASH;
            ps = con.getConexion().prepareStatement(sqlcreate);
            ps.setInt(1, last_id);
            ps.setString(2, p_hash);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return false;
    }

    public boolean hashUpdate(int user_id) {
        PreparedStatement ps;
        Random r = new Random();
        String p_hash = Long.toString(r.nextLong(), 36);
        try {
            String sqlupdate = SQL_UPDATEHASH;
            ps = con.getConexion().prepareStatement(sqlupdate);
            ps.setString(1, p_hash);
            ps.setInt(2, user_id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return false;
    }

    public int last_id() {
        PreparedStatement ps;
        int val = 0;
        try {
            String sqlauth = LAST_ID;
            ps = con.getConexion().prepareStatement(sqlauth);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                val = rs.getInt(1);
            }
            return val;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return val;
    }

    public boolean update(User user) {
        PreparedStatement ps;
        int id;
        String p_name;
        String p_username;
        String p_password;
        Role p_role;
        try {
            String sqlupdate = SQL_UPDATE;
            ps = con.getConexion().prepareStatement(sqlupdate);
            p_name = user.getName();
            p_username = user.getUsername();

            if (this.hashUpdate(user.getId())) {
                p_password = user.encrypt(user.getPassword() + this.hash(user.getId()));
                p_role = user.getRole();
                ps.setString(1, p_name);
                ps.setString(2, p_username);
                ps.setString(3, p_password);
                ps.setInt(4, p_role.getId());
                ps.setInt(5, user.getId());

                if (ps.executeUpdate() > 0) {

                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return false;
    }
}
