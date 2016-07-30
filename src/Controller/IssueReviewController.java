/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.IssueDAO;
import DAO.IssueLogDAO;
import DAO.StatusDAO;
import DAO.UserDAO;
import DTO.Issue;
import DTO.IssueLog;
import DTO.Project;
import DTO.Status;
import DTO.User;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class IssueReviewController {

    UserDAO userdao = new UserDAO();
    StatusDAO statusdao = new StatusDAO();
    IssueLogDAO issuelogdao = new IssueLogDAO();
    IssueDAO issuedao = new IssueDAO();
            

    public ArrayList<User> fetchUsers() {
        return userdao.readAll();
    }

    public ArrayList<Status> fetchStatus(Project pro) {
        return statusdao.readAllWorkflow(pro.getWorkflow().getId());
    }

    public IssueLog fetchIssueLog(int issue) {
        return issuelogdao.readOnefromIssue(issue);
    }

    public ArrayList<IssueLog> fetchIssueLogs(int issue) {
        return issuelogdao.readAllfromIssue(issue);
    }
    
    public Issue fetchIssue(int issue){
        return issuedao.read(issue);
    }
    
    public Status fetchStatusLabel(int id){
        return statusdao.read(id);
    }
    
    public boolean createLog(IssueLog issuelog){
        return issuelogdao.create(issuelog);
    }
    
    

}
