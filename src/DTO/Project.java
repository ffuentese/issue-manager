/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

/**
 *
 * @author Francisco
 */
public class Project {
    private int id;
    private String name;
    private Workflow workflow;

    public Project() {
    }

    public Project(int id, String name, Workflow workflow) {
        this.id = id;
        this.name = name;
        this.workflow = workflow;
    }
    
    

    public Project(String name) {
        this.name = name;
    }

    public Project(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }
    
    

    @Override
    public String toString() {
        return name;
    }
    
    
}
