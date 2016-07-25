/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import View.LoginView;
import View.MainView;
import java.beans.PropertyVetoException;

/**
 *
 * @author Francisco
 */
public class IssueManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyVetoException {
        // TODO code application logic here
        MainView main = new View.MainView();
        LoginView loginv = new View.LoginView();
        main.setVisible(true);

    }
    
}
