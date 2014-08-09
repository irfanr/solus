/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.ui;

import com.mascova.qeela.controller.LoginMasterController;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author irfan
 */
public class LoginMaster extends InternalFrame {

    private LoginMasterController lmc = null;
    
    /**
     * Creates new form LoginMaster
     */
    public LoginMaster(LoginMasterController lmc) {
        this.lmc = lmc;
        initComponents();
        loginGridControl.setController(lmc);
        this.setSize(750, 330);        
    }
    
    public GridControl getGrid() {

        return this.loginGridControl;
    }    
    
    private GridDataLocator getGridDataLocator() {
        return lmc;
    }    
    
    public String getVOClassName(){
        return "com.mascova.qeela.entity.Login";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        filterButton1 = new org.openswing.swing.client.FilterButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        loginGridControl = new org.openswing.swing.client.GridControl();
        idColumn = new org.openswing.swing.table.columns.client.IntegerColumn();
        usernameColumn = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        checkBoxColumn1 = new org.openswing.swing.table.columns.client.CheckBoxColumn();

        topPanel.add(insertButton1);
        topPanel.add(editButton1);
        topPanel.add(reloadButton1);
        topPanel.add(saveButton1);
        topPanel.add(deleteButton1);
        topPanel.add(exportButton1);
        topPanel.add(filterButton1);
        topPanel.add(navigatorBar1);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        loginGridControl.setDeleteButton(deleteButton1);
        loginGridControl.setEditButton(editButton1);
        loginGridControl.setGridDataLocator(this.getGridDataLocator());
        loginGridControl.setInsertButton(insertButton1);
        loginGridControl.setNavBar(navigatorBar1);
        loginGridControl.setReloadButton(reloadButton1);
        loginGridControl.setSaveButton(saveButton1);
        loginGridControl.setValueObjectClassName(getVOClassName());

        idColumn.setColumnName("id");
        idColumn.setColumnRequired(false);
        idColumn.setColumnSortable(true);
        idColumn.setHeaderColumnName("ID");
        loginGridControl.getColumnContainer().add(idColumn);

        usernameColumn.setColumnName("username");
        usernameColumn.setEditableOnEdit(true);
        usernameColumn.setEditableOnInsert(true);
        usernameColumn.setHeaderColumnName("Username");
        loginGridControl.getColumnContainer().add(usernameColumn);

        textColumn1.setColumnName("password");
        textColumn1.setEditableOnEdit(true);
        textColumn1.setEditableOnInsert(true);
        textColumn1.setEncryptText(true);
        textColumn1.setHeaderColumnName("Password");
        loginGridControl.getColumnContainer().add(textColumn1);

        checkBoxColumn1.setColumnName("status");
        checkBoxColumn1.setEditableOnEdit(true);
        checkBoxColumn1.setEditableOnInsert(true);
        checkBoxColumn1.setHeaderColumnName("Status");
        loginGridControl.getColumnContainer().add(checkBoxColumn1);

        getContentPane().add(loginGridControl, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.table.columns.client.IntegerColumn idColumn;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.GridControl loginGridControl;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private javax.swing.JPanel topPanel;
    private org.openswing.swing.table.columns.client.TextColumn usernameColumn;
    // End of variables declaration//GEN-END:variables

}