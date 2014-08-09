/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Customer;
import com.mascova.qeela.service.CustomerService;
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
public class CustomerLookupController extends LookupController {

    private static final int BLOCK_SIZE = 10;
    private ApplicationContext ac;
    private CustomerService customerService;

    public CustomerLookupController(ApplicationContext ac) {
        this.ac = ac;
        customerService = ac.getBean(CustomerService.class);
        this.setLookupDataLocator(new LookupDataLocator() {
            @Override
            public Response loadData(
                    int action,
                    int startIndex,
                    Map filteredColumns,
                    ArrayList currentSortedColumns,
                    ArrayList currentSortedVersusColumns,
                    Class valueObjectType) {

                String sql = "select c from Customer c ";
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
                    Logger.getLogger(CustomerLookupController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;

            }

            @Override
            public Response validateCode(String name) {

                Customer customer = customerService.findByName(name);

                return new VOResponse(customer);
            }

            @Override
            public Response getTreeModel(JTree jtree) {
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            }
        });

        this.setLookupValueObjectClassName("com.mascova.qeela.entity.Customer");
        this.addLookup2ParentLink("id", "customer.id");        
        this.addLookup2ParentLink("name", "customer.name");     
        this.setVisibleColumn("name", true);    
        this.setFilterableColumn("name", true);                 
//        this.setPreferredWidthColumn("name", 200);
    }
    
    
}
