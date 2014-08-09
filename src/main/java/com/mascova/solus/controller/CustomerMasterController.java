/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Customer;
import com.mascova.solus.service.CustomerService;
import com.mascova.solus.ui.CustomerMaster;
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
public class CustomerMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private CustomerMaster customerMaster = null;
    private ApplicationContext ac = null;
    private CustomerService customerService = null;
    private VillageLookupController vlc = null;    

    public CustomerMasterController(ApplicationContext ac) {
        this.ac = ac;
        customerService = ac.getBean(CustomerService.class);
        vlc = new VillageLookupController(ac);
        customerMaster = new CustomerMaster(this, vlc);
        customerMaster.setTitle("Search Customer");
        MDIFrame.add(customerMaster, true);

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

        String sql = "select p from Customer p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) customerService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Customer> customerList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            customerList = customerService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(customerList, moreRows, customerList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(CustomerMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Customer> insertedCustomerList = customerService.save(newValueObjects);

        return new VOListResponse(insertedCustomerList, false, insertedCustomerList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Customer> updatedCustomerList = customerService.update(persistentObjects);

        return new VOListResponse(updatedCustomerList, false, updatedCustomerList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        customerService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Customer customer = (Customer) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new CustomerDetailController(customerMaster, customer.getId(), ac);
    }
}
