/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.IssueDAO;
import DAO.IssueLogDAO;
import DAO.ProjectDAO;
import DAO.StatusDAO;
import DAO.UserDAO;
import DTO.Issue;
import DTO.IssueLog;
import DTO.Project;
import DTO.Status;
import DTO.User;
import DTO.Workflow;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Francisco
 */
public class IssueCreateView extends javax.swing.JInternalFrame {

    public User loguser;
    public IssueDAO issuedao;
    public IssueLogDAO issuelogdao;
    public ProjectDAO projectdao;
    public UserDAO userdao;

    /**
     * Creates new form IssueCreateView
     */
    public IssueCreateView(User user) {
        initComponents();
        loguser = user;
        projectdao = new ProjectDAO();
        userdao = new UserDAO();
        DefaultComboBoxModel modelProject,modelUsers;
        modelProject = new DefaultComboBoxModel(projectdao.readAll().toArray());
        cbProject.setModel(modelProject);
        modelUsers = new DefaultComboBoxModel(userdao.readAll().toArray());
        cbOwner.setModel(modelUsers);
        cbAssignee.setModel(modelUsers);
        // fill combobox Status
        int wflw;
        StatusDAO statusdao = new StatusDAO();
        wflw = ((Project)cbProject.getSelectedItem()).getWorkflow().getId();
        DefaultComboBoxModel modelStatus;
        modelStatus = new DefaultComboBoxModel(statusdao.readAllWorkflow(wflw).toArray());
        cbStatus.setModel(modelStatus);
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        cbStatus = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbOwner = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbAssignee = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cbProject = new javax.swing.JComboBox();
        spPriority = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnExt = new javax.swing.JButton();

        jLabel1.setText("Name");

        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNameKeyTyped(evt);
            }
        });

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Status ");

        txaDescription.setColumns(20);
        txaDescription.setLineWrap(true);
        txaDescription.setRows(5);
        txaDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txaDescriptionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txaDescription);

        jLabel3.setText("Description");

        jLabel4.setText("Owned by");

        cbOwner.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Assigned to");

        cbAssignee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Project");

        cbProject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProjectActionPerformed(evt);
            }
        });

        spPriority.setModel(new javax.swing.SpinnerNumberModel(5, 0, 10, 1));

        jLabel7.setText("Priority");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnExt.setText("Back to Menu");
        btnExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(txtName))
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExt))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cbAssignee, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(205, 205, 205))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(spPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addGap(23, 23, 23)
                                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbProject, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbOwner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbAssignee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnExt))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        Issue issue = new Issue();
        ProjectDAO projectdao = new ProjectDAO();
        Project p_project = ((Project)this.cbProject.getSelectedItem());
        UserDAO userdao = new UserDAO();
        issuedao = new IssueDAO();
        User p_owner = ((User)this.cbOwner.getSelectedItem());
        issue.setName(txtName.getText());
        issue.setCreator(loguser);
        issue.setProject(p_project);
        issue.setDate(new Date());
        issue.setOwner(p_owner);
        if (issuedao.create(issue) && issuedao.check(issue) != 0) {
            StatusDAO statusdao = new StatusDAO();
            issuelogdao = new IssueLogDAO();
            int newissue = issuedao.check(issue);
            IssueLog issuelog = new IssueLog();
            issuelog.setDescription(this.txaDescription.getText());
            issuelog.setIssue(issuedao.read(newissue));
            issuelog.setStatus((Status) this.cbStatus.getSelectedItem());
            issuelog.setDate(issue.getDate());
            issuelog.setAsignee((User) this.cbAssignee.getSelectedItem());
            issuelog.setPriority((int) this.spPriority.getValue());
            if(issuelogdao.create(issuelog)){
                JOptionPane.showMessageDialog(null, "Issue successfully added");
            } else {
                JOptionPane.showMessageDialog(null, "Problem when adding details"
                        + "to the log");
            }
        } else {
            JOptionPane.showMessageDialog(null, "There was a problem adding your"
                    + "issue to the database.");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnExtActionPerformed

    private void cbProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProjectActionPerformed
        // TODO add your handling code here:
//        this.cbStatus.setModel();
        int wflw;
        StatusDAO statusdao = new StatusDAO();
        wflw = ((Project)cbProject.getSelectedItem()).getWorkflow().getId();
        DefaultComboBoxModel modelStatus;
        modelStatus = new DefaultComboBoxModel(statusdao.readAllWorkflow(wflw).toArray());
        cbStatus.setModel(modelStatus);
    }//GEN-LAST:event_cbProjectActionPerformed

    private void txtNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyTyped
        // TODO add your handling code here:
        if(txtName.getText().length()>45){
            evt.consume();
        }
    }//GEN-LAST:event_txtNameKeyTyped

    private void txaDescriptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txaDescriptionKeyTyped
        // TODO add your handling code here:
        if(txaDescription.getText().length() > 250){
            evt.consume();
        }
    }//GEN-LAST:event_txaDescriptionKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnExt;
    private javax.swing.JComboBox cbAssignee;
    private javax.swing.JComboBox cbOwner;
    private javax.swing.JComboBox cbProject;
    private javax.swing.JComboBox cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spPriority;
    private javax.swing.JTextArea txaDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
