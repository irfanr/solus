/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.SubDistrict;
import com.mascova.solus.service.SubDistrictService;
import com.mascova.solus.ui.SubDistrictMaster;
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
public class SubDistrictMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private SubDistrictMaster subDistrictMaster = null;
    private ApplicationContext ac = null;
    private SubDistrictService subDistrictService = null;
    private DistrictLookupController dlc = null;

    public SubDistrictMasterController(ApplicationContext ac) {
        this.ac = ac;
        subDistrictService = ac.getBean(SubDistrictService.class);
        dlc = new DistrictLookupController(ac);

        subDistrictMaster = new SubDistrictMaster(this, dlc);
        subDistrictMaster.setTitle("Search SubDistrict");
        MDIFrame.add(subDistrictMaster, true);

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

        String sql = "select p from SubDistrict p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) subDistrictService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<SubDistrict> subDistrictList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            subDistrictList = subDistrictService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(subDistrictList, moreRows, subDistrictList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(SubDistrictMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<SubDistrict> insertedSubDistrictList = subDistrictService.save(newValueObjects);

        return new VOListResponse(insertedSubDistrictList, false, insertedSubDistrictList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<SubDistrict> updatedSubDistrictList = subDistrictService.update(persistentObjects);

        return new VOListResponse(updatedSubDistrictList, false, updatedSubDistrictList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        subDistrictService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        SubDistrict subDistrict = (SubDistrict) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new SubDistrictDetailController(subDistrictMaster, subDistrict.getId(), ac);
    }
}
