/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.District;
import com.mascova.solus.service.DistrictService;
import com.mascova.solus.ui.DistrictMaster;
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
public class DistrictMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private DistrictMaster districtMaster = null;
    private ApplicationContext ac = null;
    private DistrictService districtService = null;
    private ProvinceLookupController plc = null;

    public DistrictMasterController(ApplicationContext ac) {
        this.ac = ac;
        districtService = ac.getBean(DistrictService.class);
        plc = new ProvinceLookupController(ac);

        districtMaster = new DistrictMaster(this, plc);
        districtMaster.setTitle("Search District");
        MDIFrame.add(districtMaster, true);

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

        String sql = "select p from District p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) districtService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<District> districtList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            districtList = districtService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(districtList, moreRows, districtList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(DistrictMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<District> insertedDistrictList = districtService.save(newValueObjects);

        return new VOListResponse(insertedDistrictList, false, insertedDistrictList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<District> updatedDistrictList = districtService.update(persistentObjects);

        return new VOListResponse(updatedDistrictList, false, updatedDistrictList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        districtService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        District district = (District) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new DistrictDetailController(districtMaster, district.getId(), ac);
    }
}
