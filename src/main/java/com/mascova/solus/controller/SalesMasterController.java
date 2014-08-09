/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Sales;
import com.mascova.solus.service.SalesService;
import com.mascova.solus.ui.SalesMaster;
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
public class SalesMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private SalesMaster salesMaster = null;
    private ApplicationContext ac = null;
    private SalesService salesService = null;
    private CustomerLookupController clc = null;
    private EmployeeLookupController elc = null;

    public SalesMasterController(ApplicationContext ac) {
        this.ac = ac;
        salesService = ac.getBean(SalesService.class);
        clc = new CustomerLookupController(ac);
        elc = new EmployeeLookupController(ac);

        salesMaster = new SalesMaster(this, clc, elc);
        salesMaster.setTitle("Search Sales");
        MDIFrame.add(salesMaster, true);

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

        String sql = "select p from Sales p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) salesService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Sales> salesList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            salesList = salesService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(salesList, moreRows, salesList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(SalesMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Sales> insertedSalesList = salesService.update(newValueObjects);

        return new VOListResponse(insertedSalesList, false, insertedSalesList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Sales> updatedSalesList = salesService.update(persistentObjects);

        return new VOListResponse(updatedSalesList, false, updatedSalesList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        salesService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Sales sales = (Sales) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new SalesDetailController(salesMaster, sales.getId(), ac);
    }
}
