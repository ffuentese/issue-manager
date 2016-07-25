/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.WorkflowDAO;
import DTO.Workflow;

/**
 *
 * @author Francisco
 */
public class WorkflowCreateController {
    
    WorkflowDAO workflowdao = new WorkflowDAO();

    public WorkflowCreateController() {
    }
    
        public boolean createWorkflow(String name, String description)
    {
        Workflow work = new Workflow(name, description);
        if(workflowdao.create(work)){
            return true;
        }
        else {
            return false;
        }
    }
}
