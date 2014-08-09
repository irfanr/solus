/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Country;
import com.mascova.solus.service.CountryService;
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
public class CountryLookupController extends LookupController {

    private static final int BLOCK_SIZE = 10;
    private ApplicationContext ac;
    private CountryService countryService;

    public CountryLookupController(ApplicationContext ac) {
        this.ac = ac;
        countryService = ac.getBean(CountryService.class);
        this.setLookupDataLocator(new LookupDataLocator() {
            @Override
            public Response loadData(
                    int action,
                    int startIndex,
                    Map filteredColumns,
                    ArrayList currentSortedColumns,
                    ArrayList currentSortedVersusColumns,
                    Class valueObjectType) {

                String sql = "select g from Country g ";
                Object[] paramValues = new Object[]{};
                try {

                    int totalRecords = (int) countryService.countAll();

                    if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                        startIndex -= BLOCK_SIZE;
                    }
                    List<Country> countryList = null;

                    boolean moreRows = false;
                    if (startIndex + BLOCK_SIZE >= totalRecords) {
                        moreRows = false;
                    } else {
                        moreRows = true;
                    }

                    countryList = countryService.findEntries(action, startIndex, BLOCK_SIZE,
                            filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                            valueObjectType, sql, paramValues);

                    VOListResponse customVOLR = new VOListResponse(countryList, moreRows, countryList.size());

                    return customVOLR;

                } catch (Exception ex) {
                    Logger.getLogger(CountryLookupController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

            @Override
            public Response validateCode(String name) {

                Country country = countryService.findByName(name);

                return new VOResponse(country);
            }

            @Override
            public Response getTreeModel(JTree jtree) {
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            }
        });

        this.setLookupValueObjectClassName("com.mascova.solus.entity.Country");
        this.addLookup2ParentLink("id", "country.id");        
        this.addLookup2ParentLink("code", "country.code");
        this.addLookup2ParentLink("name", "country.name");     
        this.setAllColumnVisible(true);
//        this.setPreferredWidthColumn("name", 200);
    }
    
    
}
