/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.service.VillageService;
import com.mascova.qeela.ui.VillageMaster;
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
public class VillageMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private VillageMaster villageMaster = null;
    private ApplicationContext ac = null;
    private VillageService villageService = null;
    private SubDistrictLookupController sdlc = null;

    public VillageMasterController(ApplicationContext ac) {
        this.ac = ac;
        villageService = ac.getBean(VillageService.class);
        sdlc = new SubDistrictLookupController(ac);

        villageMaster = new VillageMaster(this, sdlc);
        villageMaster.setTitle("Search Village");
        MDIFrame.add(villageMaster, true);

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

        String sql = "select p from Village p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) villageService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Village> villageList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            villageList = villageService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(villageList, moreRows, villageList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(VillageMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Village> insertedVillageList = villageService.save(newValueObjects);

        return new VOListResponse(insertedVillageList, false, insertedVillageList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Village> updatedVillageList = villageService.update(persistentObjects);

        return new VOListResponse(updatedVillageList, false, updatedVillageList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        villageService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Village village = (Village) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new VillageDetailController(villageMaster, village.getId(), ac);
    }
}
