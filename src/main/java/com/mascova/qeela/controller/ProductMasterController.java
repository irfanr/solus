/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Product;
import com.mascova.qeela.service.ProductService;
import com.mascova.qeela.ui.ProductMaster;
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
public class ProductMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private ProductMaster productMaster = null;
    private ApplicationContext ac = null;
    private ProductService productService = null;

    public ProductMasterController(ApplicationContext ac) {
        this.ac = ac;
        productService = ac.getBean(ProductService.class);

        productMaster = new ProductMaster(this);
        productMaster.setTitle("Search Product");
        productMaster.setUniqueInstance(true);
        MDIFrame.add(productMaster, true);

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

        String sql = "select p from Product p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) productService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Product> productList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            productList = productService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(productList, moreRows, productList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(ProductMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Product> insertedProductList = productService.update(newValueObjects);

        return new VOListResponse(insertedProductList, false, insertedProductList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Product> updatedProductList = productService.update(persistentObjects);

        return new VOListResponse(updatedProductList, false, updatedProductList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        productService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Product product = (Product) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
                    new ProductDetailController(productMaster, product.getId(), ac);
    }

    /**
     * @return the productMaster
     */
    public ProductMaster getProductMaster() {
        return productMaster;
    }
}
