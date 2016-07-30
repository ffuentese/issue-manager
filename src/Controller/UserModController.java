/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RoleDAO;
import DAO.UserDAO;
import DTO.Role;
import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class UserModController {

    UserDAO userdao = new UserDAO();
    RoleDAO roledao = new RoleDAO();

    public UserModController() {
    }

    public ArrayList<Role> fetchRoles() {
        return roledao.readAll();
    }

    public boolean modifyUser(User user) {
        return userdao.update(user);
    }

}
