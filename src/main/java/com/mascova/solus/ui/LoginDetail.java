/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.ui;

import com.mascova.solus.controller.LoginDetailController;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.mdi.client.InternalFrame;

/**
 *
 * @author irfan
 */
public class LoginDetail extends InternalFrame {
    
    public static final int WEIGHT = 470;
    public static final int HEIGHT = 250;
    private LoginDetailController ldc = null;

    /**
     * Creates new form LoginDetail
     */
    public LoginDetail(LoginDetailController ldc) {
        this.ldc = ldc;
        initComponents();
        
        this.setResizable(false);        
    }
    
    public Form getMainPanel() {
        return mainPanel;
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginDetailPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        copyButton1 = new org.openswing.swing.client.CopyButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        mainPanel = new org.openswing.swing.form.client.Form();
        idLabelControl1 = new org.openswing.swing.client.LabelControl();
        idLabelControl2 = new org.openswing.swing.client.LabelControl();
        numericControl1 = new org.openswing.swing.client.NumericControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        idLabelControl3 = new org.openswing.swing.client.LabelControl();
        passwordControl1 = new org.openswing.swing.client.PasswordControl();
        idLabelControl4 = new org.openswing.swing.client.LabelControl();
        checkBoxControl1 = new org.openswing.swing.client.CheckBoxControl();

        loginDetailPanel.setLayout(new java.awt.BorderLayout());

        buttonsPanel.add(insertButton1);
        buttonsPanel.add(copyButton1);
        buttonsPanel.add(editButton1);
        buttonsPanel.add(reloadButton1);
        buttonsPanel.add(saveButton1);
        buttonsPanel.add(deleteButton1);

        loginDetailPanel.add(buttonsPanel, java.awt.BorderLayout.PAGE_START);

        mainPanel.setVOClassName("com.mascova.mascore.entity.Login");
        mainPanel.setCopyButton(copyButton1);
        mainPanel.setDeleteButton(deleteButton1);
        mainPanel.setEditButton(editButton1);
        mainPanel.setFormController(ldc);
        mainPanel.setInsertButton(insertButton1);
        mainPanel.setReloadButton(reloadButton1);
        mainPanel.setSaveButton(saveButton1);

        idLabelControl1.setLabel("ID");

        idLabelControl2.setLabel("Username");

        numericControl1.setAttributeName("id");
        numericControl1.setEnabledOnEdit(false);
        numericControl1.setEnabledOnInsert(false);

        textControl1.setAttributeName("username");

        idLabelControl3.setLabel("Status");

        passwordControl1.setAttributeName("password");

        idLabelControl4.setLabel("Password");

        checkBoxControl1.setAttributeName("status");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(idLabelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numericControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(idLabelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(idLabelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(idLabelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(248, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idLabelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idLabelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idLabelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        loginDetailPanel.add(mainPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(loginDetailPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private org.openswing.swing.client.CheckBoxControl checkBoxControl1;
    private org.openswing.swing.client.CopyButton copyButton1;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.LabelControl idLabelControl1;
    private org.openswing.swing.client.LabelControl idLabelControl2;
    private org.openswing.swing.client.LabelControl idLabelControl3;
    private org.openswing.swing.client.LabelControl idLabelControl4;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel loginDetailPanel;
    private org.openswing.swing.form.client.Form mainPanel;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.PasswordControl passwordControl1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextControl textControl1;
    // End of variables declaration//GEN-END:variables
}
