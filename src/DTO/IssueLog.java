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
public class IssueLog {
    private int id;
    private String name;
    private String message;
    private Issue issue;

    public IssueLog() {
    }

    public IssueLog(int id, String name, String message, Issue issue) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.issue = issue;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    
    
}
