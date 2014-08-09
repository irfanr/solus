/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Sales;
import com.mascova.qeela.service.SalesService;
import com.mascova.qeela.ui.SalesDetail;
import com.mascova.qeela.ui.SalesMaster;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author irfan
 */
public class SalesDetailController extends FormController {

    private ApplicationContext ac = null;
    private SalesService salesService = null;
    private SalesMaster salesMaster = null;
    private SalesDetail salesDetail = null;
    private long pk = -1;
    private Sales sales;   
    private CustomerLookupController clc = null;
    private EmployeeLookupController elc = null;    

    public SalesDetailController(SalesMaster salesMaster, long pk,
            ApplicationContext ac) {
        this.salesMaster = salesMaster;
        this.pk = pk;
        this.ac = ac;

        salesService = ac.getBean(SalesService.class); 
        clc = new CustomerLookupController(ac);
        elc = new EmployeeLookupController(ac);        

        salesDetail = new SalesDetail(this, clc, elc);
        salesDetail.setTitle("Sales Detail");        

        MDIFrame.add(salesDetail, true);

        if (pk != -1) {
            salesDetail.setTitle("Sales Detail");
            salesDetail.getMainPanel().setMode(Consts.READONLY);
            salesDetail.getMainPanel().reload();

        } else {
            salesDetail.setTitle("Create Sales");
            salesDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public SalesMaster getSalesMaster() {
        return salesMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = salesMaster.getGrid().getSelectedRow();

        if (row != -1) {

            sales = (Sales) salesMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = sales.getId();

            // Reload from db
            sales = salesService.find(pk);

//            // repopulate sales picture
//            if (sales.getPicture() != null) {
//                if (sales.getPicture().getFile() != null) {
//                    sales.setFile(sales.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(sales);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Sales newSales = (Sales) newPersistentObject;

        // insert sales picture
//        if (newSales.getFile() != null) {
//            newSales.setPicture(new Image());
//            newSales.getPicture().setFile(newSales.getFile());
//            imageService.saveImage(newSales.getPicture());
//        }

        salesService.save(newSales);

        return new VOResponse(newSales);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Sales modifiedSales = (Sales) persistentObject;
        Sales oldSales = (Sales) oldPersistentObject;

//        if (oldSales.getPicture() != null) {
//
//            Image salesImage = imageService.findImage(
//                    oldSales.getPicture().getId());
//            
//            // update sales picture
//            if (modifiedSales.getFile() != null) {
//
//                // update image
//                salesImage.setFile(modifiedSales.getFile());
//                imageService.updateImage(salesImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(salesImage);
//            }
//        } else {
//
//            // insert sales picture
//            if (modifiedSales.getFile() != null) {
//                
//                modifiedSales.setPicture(new Image());
//                modifiedSales.getPicture().setFile(modifiedSales.getFile());
//                imageService.saveImage(modifiedSales.getPicture());
//            }
//        }



        salesService.update(sales);

        return new VOResponse(modifiedSales);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Sales deletedSales = (Sales) persistentObject;
        salesService.delete(sales);

        return new VOResponse(deletedSales);
    }
}
