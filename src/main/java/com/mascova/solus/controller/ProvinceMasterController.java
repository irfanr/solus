/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Province;
import com.mascova.solus.service.ProvinceService;
import com.mascova.solus.ui.ProvinceMaster;
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
public class ProvinceMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private ProvinceMaster provinceMaster = null;
    private ApplicationContext ac = null;
    private ProvinceService provinceService = null;
    private CountryLookupController clc = null;

    public ProvinceMasterController(ApplicationContext ac) {
        this.ac = ac;
        provinceService = ac.getBean(ProvinceService.class);
        clc = new CountryLookupController(ac);

        provinceMaster = new ProvinceMaster(this, clc);
        provinceMaster.setTitle("Search Province");
        MDIFrame.add(provinceMaster, true);

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

        String sql = "select p from Province p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) provinceService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Province> provinceList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            provinceList = provinceService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(provinceList, moreRows, provinceList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(ProvinceMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Province> insertedProvinceList = provinceService.save(newValueObjects);

        return new VOListResponse(insertedProvinceList, false, insertedProvinceList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Province> updatedProvinceList = provinceService.update(persistentObjects);

        return new VOListResponse(updatedProvinceList, false, updatedProvinceList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        provinceService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Province province = (Province) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new ProvinceDetailController(provinceMaster, province.getId(), ac);
    }
}
