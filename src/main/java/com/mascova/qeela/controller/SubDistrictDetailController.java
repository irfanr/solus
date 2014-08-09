/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.SubDistrict;
import com.mascova.qeela.service.ImageService;
import com.mascova.qeela.service.SubDistrictService;
import com.mascova.qeela.ui.SubDistrictDetail;
import com.mascova.qeela.ui.SubDistrictMaster;
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
public class SubDistrictDetailController extends FormController {

    private ApplicationContext ac = null;
    private SubDistrictService subDistrictService = null;
    private ImageService imageService = null;
    private SubDistrictMaster subDistrictMaster = null;
    private SubDistrictDetail subDistrictDetail = null;
    private long pk = -1;
    private SubDistrict subDistrict;
    private DistrictLookupController dlc = null;

    public SubDistrictDetailController(SubDistrictMaster subDistrictMaster, long pk,
            ApplicationContext ac) {
        this.subDistrictMaster = subDistrictMaster;
        this.pk = pk;
        this.ac = ac;

        subDistrictService = ac.getBean(SubDistrictService.class);
        imageService = ac.getBean(ImageService.class);
        dlc = new DistrictLookupController(ac);

        subDistrictDetail = new SubDistrictDetail(this, dlc);

        MDIFrame.add(subDistrictDetail, true);

        if (pk != -1) {
            subDistrictDetail.setTitle("SubDistrict Detail");
            subDistrictDetail.getMainPanel().setMode(Consts.READONLY);
            subDistrictDetail.getMainPanel().reload();

        } else {
            subDistrictDetail.setTitle("Create SubDistrict");
            subDistrictDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public SubDistrictMaster getSubDistrictMaster() {
        return subDistrictMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = subDistrictMaster.getGrid().getSelectedRow();

        if (row != -1) {

            subDistrict = (SubDistrict) subDistrictMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = subDistrict.getId();

            // Reload from db
            subDistrict = subDistrictService.find(pk);

//            // repopulate subDistrict picture
//            if (subDistrict.getPicture() != null) {
//                if (subDistrict.getPicture().getFile() != null) {
//                    subDistrict.setFile(subDistrict.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(subDistrict);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        SubDistrict newSubDistrict = (SubDistrict) newPersistentObject;

        // insert subDistrict picture
//        if (newSubDistrict.getFile() != null) {
//            newSubDistrict.setPicture(new Image());
//            newSubDistrict.getPicture().setFile(newSubDistrict.getFile());
//            imageService.saveImage(newSubDistrict.getPicture());
//        }

        subDistrictService.save(newSubDistrict);

        return new VOResponse(newSubDistrict);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        SubDistrict modifiedSubDistrict = (SubDistrict) persistentObject;
        SubDistrict oldSubDistrict = (SubDistrict) oldPersistentObject;

//        if (oldSubDistrict.getPicture() != null) {
//
//            Image subDistrictImage = imageService.findImage(
//                    oldSubDistrict.getPicture().getId());
//            
//            // update subDistrict picture
//            if (modifiedSubDistrict.getFile() != null) {
//
//                // update image
//                subDistrictImage.setFile(modifiedSubDistrict.getFile());
//                imageService.updateImage(subDistrictImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(subDistrictImage);
//            }
//        } else {
//
//            // insert subDistrict picture
//            if (modifiedSubDistrict.getFile() != null) {
//                
//                modifiedSubDistrict.setPicture(new Image());
//                modifiedSubDistrict.getPicture().setFile(modifiedSubDistrict.getFile());
//                imageService.saveImage(modifiedSubDistrict.getPicture());
//            }
//        }



        subDistrictService.update(subDistrict);

        return new VOResponse(modifiedSubDistrict);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        SubDistrict deletedSubDistrict = (SubDistrict) persistentObject;
        subDistrictService.delete(subDistrict);

        return new VOResponse(deletedSubDistrict);
    }
}
