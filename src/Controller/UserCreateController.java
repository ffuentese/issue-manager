/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.RoleDAO;
import DAO.UserDAO;
import DTO.User;
import DTO.Role;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class UserCreateController {
UserDAO userdao = new UserDAO();
RoleDAO roledao = new RoleDAO();

    public UserCreateController() {
    }
    
    public boolean createUser(User user){
        return userdao.create(user);
    }
    
    public ArrayList<Role> fetchRoles(){
        return roledao.readAll();
    }
}
