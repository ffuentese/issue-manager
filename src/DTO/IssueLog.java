/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import java.util.Date;

/**
 *
 * @author Francisco
 */
public class IssueLog {

    private int id;
    private String description;
    private Issue issue;
    private Status status;
    private Date date;
    private User asignee;
    private int priority;
    

    public IssueLog() {
    }

    public IssueLog(int id, String description, Issue issue, Status status, Date date, User asignee, int priority) {
        this.id = id;
        this.description = description;
        this.issue = issue;
        this.status = status;
        this.date = date;
        this.asignee = asignee;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAsignee() {
        return asignee;
    }

    public void setAsignee(User asignee) {
        this.asignee = asignee;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    
}
