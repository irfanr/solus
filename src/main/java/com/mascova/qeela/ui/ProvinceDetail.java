/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.ui;

import com.mascova.qeela.controller.CountryLookupController;
import com.mascova.qeela.controller.ProvinceDetailController;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.mdi.client.InternalFrame;

/**
 *
 * @author irfan
 */
public class ProvinceDetail extends InternalFrame {

    /**
     * Creates new form ProvinceDetail
     */
   public static final int WEIGHT = 470;
    public static final int HEIGHT = 250;
    private ProvinceDetailController pdc = null;
    private CountryLookupController clc = null;

    public ProvinceDetail(ProvinceDetailController pdc, CountryLookupController clc) {
        this.pdc = pdc;
        this.clc = clc;
        initComponents();
        countryCodLookupControl.setLookupController(clc);
//        this.setSize(WEIGHT, HEIGHT);  
        this.setResizable(false);

    }
    
    public Form getMainPanel() {
        return mainPanel;
    }    
    
    private String getValueObjectClassName() {
        return "com.mascova.qeela.entity.Province";
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detailPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        copyButton1 = new org.openswing.swing.client.CopyButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        mainPanel = new org.openswing.swing.form.client.Form();
        idNumericControl = new org.openswing.swing.client.NumericControl();
        codeTextControl = new org.openswing.swing.client.TextControl();
        nameTextControl = new org.openswing.swing.client.TextControl();
        countryCodLookupControl = new org.openswing.swing.client.CodLookupControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();

        detailPanel.setLayout(new java.awt.BorderLayout());

        buttonsPanel.add(insertButton1);
        buttonsPanel.add(copyButton1);
        buttonsPanel.add(editButton1);
        buttonsPanel.add(reloadButton1);
        buttonsPanel.add(saveButton1);
        buttonsPanel.add(deleteButton1);

        detailPanel.add(buttonsPanel, java.awt.BorderLayout.PAGE_START);

        mainPanel.setVOClassName(getValueObjectClassName());
        mainPanel.setCopyButton(copyButton1        );
        mainPanel.setDeleteButton(deleteButton1);
        mainPanel.setEditButton(editButton1);
        mainPanel.setFormController(pdc);
        mainPanel.setInsertButton(insertButton1);
        mainPanel.setReloadButton(reloadButton1);
        mainPanel.setSaveButton(saveButton1);

        idNumericControl.setAttributeName("id");
        idNumericControl.setEnabled(false);
        idNumericControl.setEnabledOnEdit(false);
        idNumericControl.setEnabledOnInsert(false);

        codeTextControl.setAttributeName("code");

        nameTextControl.setAttributeName("name");

        countryCodLookupControl.setAttributeName("country.name");
        countryCodLookupControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryCodLookupControlActionPerformed(evt);
            }
        });

        labelControl1.setLabel("Name");

        labelControl2.setLabel("Country");

        labelControl3.setLabel("ID");

        labelControl4.setLabel("Code");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(countryCodLookupControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeTextControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idNumericControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idNumericControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelControl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(codeTextControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelControl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameTextControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(countryCodLookupControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        detailPanel.add(mainPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(detailPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void countryCodLookupControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryCodLookupControlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countryCodLookupControlActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private org.openswing.swing.client.TextControl codeTextControl;
    private org.openswing.swing.client.CopyButton copyButton1;
    private org.openswing.swing.client.CodLookupControl countryCodLookupControl;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private javax.swing.JPanel detailPanel;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.NumericControl idNumericControl;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.form.client.Form mainPanel;
    private org.openswing.swing.client.TextControl nameTextControl;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    // End of variables declaration//GEN-END:variables
}
