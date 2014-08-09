/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Service;
import com.mascova.solus.service.ServiceService;
import com.mascova.solus.ui.ServiceMaster;
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
public class ServiceMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private ServiceMaster serviceMaster = null;
    private ApplicationContext ac = null;
    private ServiceService serviceService = null;

    public ServiceMasterController(ApplicationContext ac) {
        this.ac = ac;
        serviceService = ac.getBean(ServiceService.class);

        serviceMaster = new ServiceMaster(this);
        serviceMaster.setTitle("Search Service");
        MDIFrame.add(serviceMaster, true);

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

        String sql = "select p from Service p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) serviceService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Service> serviceList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            serviceList = serviceService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(serviceList, moreRows, serviceList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(ServiceMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Service> insertedServiceList = serviceService.update(newValueObjects);

        return new VOListResponse(insertedServiceList, false, insertedServiceList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Service> updatedServiceList = serviceService.update(persistentObjects);

        return new VOListResponse(updatedServiceList, false, updatedServiceList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        serviceService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Service service = (Service) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new ServiceDetailController(serviceMaster, service.getId(), ac);
    }
}
