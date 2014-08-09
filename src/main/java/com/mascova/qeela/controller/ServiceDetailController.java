/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Service;
import com.mascova.qeela.service.ServiceService;
import com.mascova.qeela.ui.ServiceDetail;
import com.mascova.qeela.ui.ServiceMaster;
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
public class ServiceDetailController extends FormController {

    private ApplicationContext ac = null;
    private ServiceService serviceService = null;
    private ServiceMaster serviceMaster = null;
    private ServiceDetail serviceDetail = null;
    private long pk = -1;
    private Service service;   

    public ServiceDetailController(ServiceMaster serviceMaster, long pk,
            ApplicationContext ac) {
        this.serviceMaster = serviceMaster;
        this.pk = pk;
        this.ac = ac;

        serviceService = ac.getBean(ServiceService.class);      

        serviceDetail = new ServiceDetail(this);
        serviceDetail.setTitle("Service Detail");        

        MDIFrame.add(serviceDetail, true);

        if (pk != -1) {
            serviceDetail.setTitle("Service Detail");
            serviceDetail.getMainPanel().setMode(Consts.READONLY);
            serviceDetail.getMainPanel().reload();

        } else {
            serviceDetail.setTitle("Create Service");
            serviceDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public ServiceMaster getServiceMaster() {
        return serviceMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = serviceMaster.getGrid().getSelectedRow();

        if (row != -1) {

            service = (Service) serviceMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = service.getId();

            // Reload from db
            service = serviceService.find(pk);

//            // repopulate service picture
//            if (service.getPicture() != null) {
//                if (service.getPicture().getFile() != null) {
//                    service.setFile(service.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(service);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Service newService = (Service) newPersistentObject;

        // insert service picture
//        if (newService.getFile() != null) {
//            newService.setPicture(new Image());
//            newService.getPicture().setFile(newService.getFile());
//            imageService.saveImage(newService.getPicture());
//        }

        serviceService.save(newService);

        return new VOResponse(newService);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Service modifiedService = (Service) persistentObject;
        Service oldService = (Service) oldPersistentObject;

//        if (oldService.getPicture() != null) {
//
//            Image serviceImage = imageService.findImage(
//                    oldService.getPicture().getId());
//            
//            // update service picture
//            if (modifiedService.getFile() != null) {
//
//                // update image
//                serviceImage.setFile(modifiedService.getFile());
//                imageService.updateImage(serviceImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(serviceImage);
//            }
//        } else {
//
//            // insert service picture
//            if (modifiedService.getFile() != null) {
//                
//                modifiedService.setPicture(new Image());
//                modifiedService.getPicture().setFile(modifiedService.getFile());
//                imageService.saveImage(modifiedService.getPicture());
//            }
//        }



        serviceService.update(service);

        return new VOResponse(modifiedService);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Service deletedService = (Service) persistentObject;
        serviceService.delete(service);

        return new VOResponse(deletedService);
    }
}
