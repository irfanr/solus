/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Person;
import com.mascova.solus.service.PersonService;
import com.mascova.solus.ui.PersonMaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author irfan
 */
public class PersonMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private PersonMaster personMaster = null;
    private ApplicationContext ac = null;
    private PersonService personService = null;
    private VillageLookupController vlc = null;

    public PersonMasterController(ApplicationContext ac) {
        this.ac = ac;
        personService = ac.getBean(PersonService.class);
        vlc = new VillageLookupController(ac);

        personMaster = new PersonMaster(this, vlc);
        personMaster.setTitle("Search Person");
        MDIFrame.add(personMaster, true);

    }

    @Override
    public Response loadData(
            int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            Map otherGridParams) {

        String sql = "select p from Person p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) personService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Person> personList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            personList = personService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(personList, moreRows, personList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(PersonMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Person> insertedPersonList = personService.update(newValueObjects);

        return new VOListResponse(insertedPersonList, false, insertedPersonList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Person> updatedPersonList = personService.update(persistentObjects);

        return new VOListResponse(updatedPersonList, false, updatedPersonList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        personService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Person person = (Person) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new PersonDetailController(personMaster, person.getId(), ac);
    }
}
