/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.service.VillageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.lookup.client.LookupDataLocator;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.tree.java.OpenSwingTreeNode;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author irfan
 */
public class VillageLookupController extends LookupController {

    private static final int BLOCK_SIZE = 10;
    private ApplicationContext ac;
    private VillageService villageService;

    public VillageLookupController(ApplicationContext ac) {
        this.ac = ac;
        villageService = ac.getBean(VillageService.class);
        this.setLookupDataLocator(new LookupDataLocator() {
            @Override
            public Response loadData(
                    int action,
                    int startIndex,
                    Map filteredColumns,
                    ArrayList currentSortedColumns,
                    ArrayList currentSortedVersusColumns,
                    Class valueObjectType) {

                String sql = "select g from Village g ";
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
                    Logger.getLogger(VillageLookupController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

            @Override
            public Response validateCode(String name) {

                Village village = villageService.findByName(name);

                return new VOResponse(village);
            }

            @Override
            public Response getTreeModel(JTree jtree) {
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            }
        });

        this.setLookupValueObjectClassName("com.mascova.qeela.entity.Village");
        this.addLookup2ParentLink("id", "village.id");        
        this.addLookup2ParentLink("code", "village.code");
        this.addLookup2ParentLink("name", "village.name");     
        this.setVisibleColumn("code", true);
        this.setVisibleColumn("name", true);    
        this.setVisibleColumn("subDistrict.name", true); 
        this.setVisibleColumn("subDistrict.district.name", true); 
        this.setVisibleColumn("subDistrict.district.province.name", true);    
        this.setFilterableColumn("code", true);
        this.setFilterableColumn("name", true);        
        this.setFilterableColumn("subDistrict.name", true); 
        this.setFilterableColumn("subDistrict.district.name", true); 
        this.setFilterableColumn("subDistrict.district.province.name", true);            
//        this.setPreferredWidthColumn("name", 200);
    }
    
    
}
