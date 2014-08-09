/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Customer;
import com.mascova.qeela.service.ImageService;
import com.mascova.qeela.service.CustomerService;
import com.mascova.qeela.ui.CustomerDetail;
import com.mascova.qeela.ui.CustomerMaster;
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
public class CustomerDetailController extends FormController {

    private ApplicationContext ac = null;
    private CustomerService customerService = null;
    private CustomerMaster customerMaster = null;
    private CustomerDetail customerDetail = null;
    private long pk = -1;
    private Customer customer;
    private VillageLookupController vlc = null;    

    public CustomerDetailController(CustomerMaster customerMaster, long pk,
            ApplicationContext ac) {
        this.customerMaster = customerMaster;
        this.pk = pk;
        this.ac = ac;

        customerService = ac.getBean(CustomerService.class);
        vlc = new VillageLookupController(ac);        

        customerDetail = new CustomerDetail(this, vlc);

        MDIFrame.add(customerDetail, true);

        if (pk != -1) {
            customerDetail.setTitle("Customer Detail");
            customerDetail.getMainPanel().setMode(Consts.READONLY);
            customerDetail.getMainPanel().reload();

        } else {
            customerDetail.setTitle("Create Customer");
            customerDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public CustomerMaster getCustomerMaster() {
        return customerMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = customerMaster.getGrid().getSelectedRow();

        if (row != -1) {

            customer = (Customer) customerMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = customer.getId();

            // Reload from db
            customer = customerService.find(pk);

//            // repopulate customer picture
//            if (customer.getPicture() != null) {
//                if (customer.getPicture().getFile() != null) {
//                    customer.setFile(customer.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(customer);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Customer newCustomer = (Customer) newPersistentObject;

        // insert customer picture
//        if (newCustomer.getFile() != null) {
//            newCustomer.setPicture(new Image());
//            newCustomer.getPicture().setFile(newCustomer.getFile());
//            imageService.saveImage(newCustomer.getPicture());
//        }

        customerService.save(newCustomer);

        return new VOResponse(newCustomer);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Customer modifiedCustomer = (Customer) persistentObject;
        Customer oldCustomer = (Customer) oldPersistentObject;

//        if (oldCustomer.getPicture() != null) {
//
//            Image customerImage = imageService.findImage(
//                    oldCustomer.getPicture().getId());
//            
//            // update customer picture
//            if (modifiedCustomer.getFile() != null) {
//
//                // update image
//                customerImage.setFile(modifiedCustomer.getFile());
//                imageService.updateImage(customerImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(customerImage);
//            }
//        } else {
//
//            // insert customer picture
//            if (modifiedCustomer.getFile() != null) {
//                
//                modifiedCustomer.setPicture(new Image());
//                modifiedCustomer.getPicture().setFile(modifiedCustomer.getFile());
//                imageService.saveImage(modifiedCustomer.getPicture());
//            }
//        }



        customerService.update(customer);

        return new VOResponse(modifiedCustomer);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Customer deletedCustomer = (Customer) persistentObject;
        customerService.delete(customer);

        return new VOResponse(deletedCustomer);
    }
}
