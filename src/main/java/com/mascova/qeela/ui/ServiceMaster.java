/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.ui;

import com.mascova.qeela.controller.ServiceMasterController;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author irfan
 */
public class ServiceMaster extends InternalFrame {
    
    private ServiceMasterController smc = null;       

     /**
     * Creates new form ServiceMaster
     */
    public ServiceMaster(ServiceMasterController pmc) {
        
        this.smc = pmc;
        initComponents();
        gridControl.setController(pmc);        
        
        this.setSize(750, 330);        
    }
    
    public GridControl getGrid() {

        return this.gridControl;
    }

    private GridDataLocator getGridDataLocator() {

        return smc;
    }

    private String getValueObjectClassName() {
        return "com.mascova.qeela.entity.Service";
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
        gridControl = new org.openswing.swing.client.GridControl();
        idColumn = new org.openswing.swing.table.columns.client.IntegerColumn();
        nameColumn = new org.openswing.swing.table.columns.client.TextColumn();
        currencyColumn1 = new org.openswing.swing.table.columns.client.CurrencyColumn();

        topPanel.add(insertButton1);
        topPanel.add(editButton1);
        topPanel.add(reloadButton1);
        topPanel.add(saveButton1);
        topPanel.add(deleteButton1);
        topPanel.add(exportButton1);
        topPanel.add(filterButton1);
        topPanel.add(navigatorBar1);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        gridControl.setDeleteButton(deleteButton1       );
        gridControl.setEditButton(editButton1);
        gridControl.setExportButton(exportButton1);
        gridControl.setFilterButton(filterButton1);
        gridControl.setGridDataLocator(this.getGridDataLocator());
        gridControl.setInsertButton(insertButton1);
        gridControl.setNavBar(navigatorBar1);
        gridControl.setReloadButton(reloadButton1);
        gridControl.setSaveButton(saveButton1);
        gridControl.setValueObjectClassName(getValueObjectClassName());

        idColumn.setColumnName("id");
        idColumn.setColumnRequired(false);
        idColumn.setHeaderColumnName("ID");
        gridControl.getColumnContainer().add(idColumn);

        nameColumn.setColumnName("name");
        nameColumn.setEditableOnEdit(true);
        nameColumn.setEditableOnInsert(true);
        nameColumn.setHeaderColumnName("Name");
        gridControl.getColumnContainer().add(nameColumn);

        currencyColumn1.setColumnName("price");
        currencyColumn1.setEditableOnEdit(true);
        currencyColumn1.setEditableOnInsert(true);
        currencyColumn1.setHeaderColumnName("Price");
        gridControl.getColumnContainer().add(currencyColumn1);

        getContentPane().add(gridControl, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn1;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridControl;
    private org.openswing.swing.table.columns.client.IntegerColumn idColumn;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.table.columns.client.TextColumn nameColumn;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}