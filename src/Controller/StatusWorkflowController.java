/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.StatusDAO;
import DTO.Status;
import DTO.Workflow;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class StatusWorkflowController {

    StatusDAO statusdao = new StatusDAO();

    public StatusWorkflowController() {
    }

    public ArrayList<Status> fetchStatus(Workflow wf) {
        int value = wf.getId();
        return statusdao.readAllWorkflow(value);
    }
    
    public boolean createStatus(Status st){
        return statusdao.create(st);
    }
}
