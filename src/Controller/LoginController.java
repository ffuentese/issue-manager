/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.UserDAO;
import DTO.User;
import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Francisco
 */
public class LoginController {
    UserDAO userdao = new UserDAO();

    public LoginController() {
    }
    
    
    
    public User loginpass(String user, String pass){
        User userobj = userdao.readAuth(user);
        String hash = null;
        String password = null;
        if (userobj!=null){
            hash = userdao.hash(userobj.getId());
            System.out.println("Hsah en db " + hash);
            System.out.println("En db " + userobj.getPassword());
            password = userobj.encrypt(pass+hash);
            System.out.println("Digitado " + password);
            if(userobj.getPassword().equals(password)){
                userobj.setPassword(null);
                return userobj;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
