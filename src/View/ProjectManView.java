/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ProjectManController;
import DAO.ProjectDAO;
import DAO.WorkflowDAO;
import DTO.Project;
import DTO.User;
import DTO.Workflow;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco
 */
public class ProjectManView extends javax.swing.JInternalFrame {

    public User loguser;
    public WorkflowDAO workflowdao;
    ProjectManController pmc = new ProjectManController();
    String col[] = {"Id", "Name", "Workflow"};
    DefaultTableModel tablemodel = new DefaultTableModel(col, 0);

    /**
     * Creates new form ProjectManView
     */
    public ProjectManView(User user) {
        initComponents();
        loguser = user;
        this.fillTable();
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        workflowdao = new WorkflowDAO();
        DefaultComboBoxModel modelProject;
        modelProject = new DefaultComboBoxModel(workflowdao.readAll().toArray());
        cbWorkflow.setModel(modelProject);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnMenu1 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbWorkflow = new javax.swing.JComboBox();

        btnMenu.setText("Add a new Project");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        jTable1.setModel(tablemodel);
        jScrollPane1.setViewportView(jTable1);

        btnMenu1.setText("Back to menu");
        btnMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenu1ActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete selected");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel1.setText("When adding a new project use this workflow");

        cbWorkflow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnMenu)
                            .addGap(60, 60, 60)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMenu1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbWorkflow, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbWorkflow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMenu)
                    .addComponent(btnMenu1)
                    .addComponent(btnDelete))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        Project pro = new Project();
        String name = JOptionPane.showInputDialog("Please name the new project");
        Workflow workflow = ((Workflow)cbWorkflow.getSelectedItem());
        pro.setName(name);
        pro.setWorkflow(workflow);
        if (pmc.createProject(pro)) {
            JOptionPane.showMessageDialog(null, "Project added");
            this.reset();
            this.fillTable();
        } else {
            JOptionPane.showMessageDialog(null, "An error was found. The project couldn't be added");
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnMenu1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (resp == JOptionPane.YES_OPTION) {
            int id = jTable1.getSelectedRow();
            String val = (String) jTable1.getModel().getValueAt(id, 0);
            System.out.println(val);
            int index = Integer.parseInt(val);
            if (pmc.delete(index)) {
                JOptionPane.showMessageDialog(null, "The project was deleted.");
                this.reset();
                this.fillTable();
            } else {
                JOptionPane.showMessageDialog(null, "There was a problem trying to delete that project.");
            };
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void fillTable() {
        if (pmc.fetchProjects().size() > 0) {
            for (int i = 0; i < pmc.fetchProjects().size(); i++) {
                String[] linea = {Integer.toString(pmc.fetchProjects().get(i).getId()), 
                    pmc.fetchProjects().get(i).getName(), 
                    pmc.fetchProjects().get(i).getWorkflow().getName()};
                tablemodel.addRow(linea);
            }
        }
    }

    private void reset() {
        tablemodel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnMenu1;
    private javax.swing.JComboBox cbWorkflow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
