/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RoleDAO;
import DAO.UserDAO;
import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class UserManController {
    UserDAO userdao = new UserDAO();
    RoleDAO roledao = new RoleDAO();

    public UserManController() {
    }
    
    public ArrayList<User> fetchUsers(){
        return userdao.readAll();
    }
    
    public User fetchUser(int user){
        return userdao.read(user);
    }
}
