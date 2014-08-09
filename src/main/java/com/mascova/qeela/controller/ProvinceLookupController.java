/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Province;
import com.mascova.qeela.service.ProvinceService;
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
public class ProvinceLookupController extends LookupController {

    private static final int BLOCK_SIZE = 10;
    private ApplicationContext ac;
    private ProvinceService provinceService;

    public ProvinceLookupController(ApplicationContext ac) {
        this.ac = ac;
        provinceService = ac.getBean(ProvinceService.class);
        this.setLookupDataLocator(new LookupDataLocator() {
            @Override
            public Response loadData(
                    int action,
                    int startIndex,
                    Map filteredColumns,
                    ArrayList currentSortedColumns,
                    ArrayList currentSortedVersusColumns,
                    Class valueObjectType) {

                String sql = "select g from Province g ";
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
            public Response validateCode(String name) {

                Province province = provinceService.findByName(name);

                return new VOResponse(province);
            }

            @Override
            public Response getTreeModel(JTree jtree) {
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            }
        });

        this.setLookupValueObjectClassName("com.mascova.qeela.entity.Province");
        this.addLookup2ParentLink("id", "province.id");        
        this.addLookup2ParentLink("code", "province.code");
        this.addLookup2ParentLink("name", "province.name");     
        this.setAllColumnVisible(true);
//        this.setPreferredWidthColumn("name", 200);
    }
    
    
}
