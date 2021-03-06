/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.ui;

import com.mascova.solus.controller.PersonDetailController;
import com.mascova.solus.controller.VillageLookupController;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.mdi.client.InternalFrame;

/**
 *
 * @author irfan
 */
public class PersonDetail extends InternalFrame {

    public static final int WEIGHT = 470;
    public static final int HEIGHT = 250;
    private PersonDetailController pdc = null;
    private VillageLookupController vlc = null;

    public PersonDetail(PersonDetailController pdc, VillageLookupController vlc) {
        this.pdc = pdc;
        this.vlc = vlc;
        initComponents();
        villageCodLookupControl.setLookupController(vlc);
//        this.setSize(WEIGHT, HEIGHT);  
        this.setResizable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personDetailPanel = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        copyButton1 = new org.openswing.swing.client.CopyButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        mainPanel = new org.openswing.swing.form.client.Form();
        idLabelControl1 = new org.openswing.swing.client.LabelControl();
        idNumericControl = new org.openswing.swing.client.NumericControl();
        name1LabelControl = new org.openswing.swing.client.LabelControl();
        name1Control = new org.openswing.swing.client.TextControl();
        name1LabelControl1 = new org.openswing.swing.client.LabelControl();
        name1Control1 = new org.openswing.swing.client.TextControl();
        name1Control2 = new org.openswing.swing.client.TextControl();
        name1LabelControl2 = new org.openswing.swing.client.LabelControl();
        name1Control3 = new org.openswing.swing.client.TextControl();
        name1LabelControl3 = new org.openswing.swing.client.LabelControl();
        imageControl1 = new org.openswing.swing.client.ImageControl();
        addressLabelControl = new org.openswing.swing.client.LabelControl();
        addressTextAreaControl = new org.openswing.swing.client.TextAreaControl();
        npwpLabelControl = new org.openswing.swing.client.LabelControl();
        npwpControl = new org.openswing.swing.client.TextControl();
        passportNoLabelControl = new org.openswing.swing.client.LabelControl();
        passportNoControl = new org.openswing.swing.client.TextControl();
        placeOfBirthLabelControl = new org.openswing.swing.client.LabelControl();
        placeOfBirthControl = new org.openswing.swing.client.TextControl();
        dateOfBirthLabelControl = new org.openswing.swing.client.LabelControl();
        dateOfBirthControl = new org.openswing.swing.client.DateControl();
        motherMaidenLabelControl = new org.openswing.swing.client.LabelControl();
        motherMaidenNoControl = new org.openswing.swing.client.TextControl();
        genderLabelControl = new org.openswing.swing.client.LabelControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        addressLabelControl1 = new org.openswing.swing.client.LabelControl();
        villageCodLookupControl = new org.openswing.swing.client.CodLookupControl();

        personDetailPanel.setLayout(new java.awt.BorderLayout());

        buttonsPanel.add(insertButton1);
        buttonsPanel.add(copyButton1);
        buttonsPanel.add(editButton1);
        buttonsPanel.add(reloadButton1);
        buttonsPanel.add(saveButton1);
        buttonsPanel.add(deleteButton1);

        personDetailPanel.add(buttonsPanel, java.awt.BorderLayout.PAGE_START);

        mainPanel.setVOClassName("com.mascova.mascore.entity.Person");
        mainPanel.setDeleteButton(deleteButton1);
        mainPanel.setEditButton(editButton1);
        mainPanel.setFormController(pdc);
        mainPanel.setInsertButton(insertButton1);
        mainPanel.setReloadButton(reloadButton1);
        mainPanel.setSaveButton(saveButton1);

        idLabelControl1.setLabel("ID");

        idNumericControl.setAttributeName("id");
        idNumericControl.setEnabledOnEdit(false);
        idNumericControl.setEnabledOnInsert(false);
        idNumericControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idNumericControlActionPerformed(evt);
            }
        });

        name1LabelControl.setLabel("Name 1");

        name1Control.setAttributeName("name1");

        name1LabelControl1.setLabel("Name 2");

        name1Control1.setAttributeName("name2");

        name1Control2.setAttributeName("name3");

        name1LabelControl2.setLabel("Name 3");

        name1Control3.setAttributeName("name4");

        name1LabelControl3.setLabel("Name 4");

        imageControl1.setAttributeName("picture");

        addressLabelControl.setLabel("Address");

        addressTextAreaControl.setAttributeName("address");
        addressTextAreaControl.setLinkLabel(addressLabelControl);
        addressTextAreaControl.setRequired(true);

        npwpLabelControl.setLabel("NPWP");

        npwpControl.setAttributeName("npwp");

        passportNoLabelControl.setLabel("Passport No");

        passportNoControl.setAttributeName("passportNo");

        placeOfBirthLabelControl.setLabel("Place of Birth");
        placeOfBirthLabelControl.setToolTipText("");

        placeOfBirthControl.setAttributeName("placeOfBirth");

        dateOfBirthLabelControl.setLabel("Date of Birth");
        dateOfBirthLabelControl.setToolTipText("");

        dateOfBirthControl.setAttributeName("dob");

        motherMaidenLabelControl.setLabel("Mother Maiden");

        motherMaidenNoControl.setAttributeName("motherMaiden");

        genderLabelControl.setLabel("Gender");

        comboBoxControl1.setAttributeName("gender");
        comboBoxControl1.setDomainId("GNDR");

        addressLabelControl1.setLabel("Village");

        villageCodLookupControl.setAttributeName("village.name");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name1LabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idLabelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name1LabelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name1LabelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name1LabelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(name1Control3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name1Control2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name1Control1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idNumericControl, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name1Control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(addressLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addressTextAreaControl, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(npwpLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(npwpControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(passportNoLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passportNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(placeOfBirthLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(placeOfBirthControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(dateOfBirthLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateOfBirthControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                    .addComponent(genderLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboBoxControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                    .addComponent(motherMaidenLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(motherMaidenNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(addressLabelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(villageCodLookupControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(imageControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(idLabelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(name1LabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(name1Control1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(name1Control2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(name1LabelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(name1LabelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(idNumericControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(name1Control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(name1Control3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(name1LabelControl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(npwpLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(npwpControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passportNoLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passportNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressTextAreaControl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabelControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(villageCodLookupControl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(placeOfBirthLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(placeOfBirthControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateOfBirthLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOfBirthControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(motherMaidenLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(motherMaidenNoControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(genderLabelControl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        personDetailPanel.add(mainPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(personDetailPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void idNumericControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idNumericControlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idNumericControlActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.LabelControl addressLabelControl;
    private org.openswing.swing.client.LabelControl addressLabelControl1;
    private org.openswing.swing.client.TextAreaControl addressTextAreaControl;
    private javax.swing.JPanel buttonsPanel;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.client.CopyButton copyButton1;
    private org.openswing.swing.client.DateControl dateOfBirthControl;
    private org.openswing.swing.client.LabelControl dateOfBirthLabelControl;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.LabelControl genderLabelControl;
    private org.openswing.swing.client.LabelControl idLabelControl1;
    private org.openswing.swing.client.NumericControl idNumericControl;
    private org.openswing.swing.client.ImageControl imageControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.form.client.Form mainPanel;
    private org.openswing.swing.client.LabelControl motherMaidenLabelControl;
    private org.openswing.swing.client.TextControl motherMaidenNoControl;
    private org.openswing.swing.client.TextControl name1Control;
    private org.openswing.swing.client.TextControl name1Control1;
    private org.openswing.swing.client.TextControl name1Control2;
    private org.openswing.swing.client.TextControl name1Control3;
    private org.openswing.swing.client.LabelControl name1LabelControl;
    private org.openswing.swing.client.LabelControl name1LabelControl1;
    private org.openswing.swing.client.LabelControl name1LabelControl2;
    private org.openswing.swing.client.LabelControl name1LabelControl3;
    private org.openswing.swing.client.TextControl npwpControl;
    private org.openswing.swing.client.LabelControl npwpLabelControl;
    private org.openswing.swing.client.TextControl passportNoControl;
    private org.openswing.swing.client.LabelControl passportNoLabelControl;
    private javax.swing.JPanel personDetailPanel;
    private org.openswing.swing.client.TextControl placeOfBirthControl;
    private org.openswing.swing.client.LabelControl placeOfBirthLabelControl;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.CodLookupControl villageCodLookupControl;
    // End of variables declaration//GEN-END:variables

    public Form getMainPanel() {
        return mainPanel;
    }
}
