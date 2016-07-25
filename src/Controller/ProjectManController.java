/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.ProjectDAO;
import DTO.Project;
import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class ProjectManController {
    ProjectDAO projectdao = new ProjectDAO();

    public ProjectManController() {
    }
    
    public ArrayList<Project> fetchProjects(){
        return projectdao.readAll();
    }
    
    public boolean createProject(String name)
    {
        Project pro = new Project(name);
        if(projectdao.create(pro)){
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean canBeDeleted(){
       // To be implemented soon
        //
        return true;
    }
    public boolean delete(int id)
    {
        
            Project pro = new Project(id);
            if(projectdao.delete(pro)){
                return true;
            }
            else {
                return false;
            }
        
        }
    }
    
