/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.IssueDAO;
import DAO.IssueLogDAO;
import DTO.Issue;
import DTO.IssueLog;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class IssueManController {
    IssueLogDAO  issuelogdao = new IssueLogDAO();
    IssueDAO issuedao = new IssueDAO();

    public IssueManController() {
    }
    
    public ArrayList<Issue> fetchIssues(){
        return issuedao.readAll();
    }
    
    public Issue fetchIssue(int i){
        return issuedao.read(i);
    }
}
