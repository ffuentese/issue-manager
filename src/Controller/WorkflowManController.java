/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.WorkflowDAO;
import DTO.Workflow;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class WorkflowManController {
    WorkflowDAO workflowdao = new WorkflowDAO();

    public WorkflowManController() {
    }
    
    public boolean createWorkflow(String name, String description){
        Workflow work = new Workflow(name, description);
        if(workflowdao.create(work)){
            return true;
        }
        else {
            return false;
        }
    }
    
    public Workflow read(int id){
        return workflowdao.read(id);
    }
    
    public ArrayList<Workflow> fetchWorkflows(){
        return workflowdao.readAll();
    }
}
