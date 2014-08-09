/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.District;
import com.mascova.solus.service.ImageService;
import com.mascova.solus.service.DistrictService;
import com.mascova.solus.ui.DistrictDetail;
import com.mascova.solus.ui.DistrictMaster;
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
public class DistrictDetailController extends FormController {

    private ApplicationContext ac = null;
    private DistrictService districtService = null;
    private ImageService imageService = null;
    private DistrictMaster districtMaster = null;
    private DistrictDetail districtDetail = null;
    private long pk = -1;
    private District district;
    private ProvinceLookupController plc = null;

    public DistrictDetailController(DistrictMaster districtMaster, long pk,
            ApplicationContext ac) {
        this.districtMaster = districtMaster;
        this.pk = pk;
        this.ac = ac;

        districtService = ac.getBean(DistrictService.class);
        imageService = ac.getBean(ImageService.class);
        plc = new ProvinceLookupController(ac);

        districtDetail = new DistrictDetail(this, plc);

        MDIFrame.add(districtDetail, true);

        if (pk != -1) {
            districtDetail.setTitle("District Detail");
            districtDetail.getMainPanel().setMode(Consts.READONLY);
            districtDetail.getMainPanel().reload();

        } else {
            districtDetail.setTitle("Create District");
            districtDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public DistrictMaster getDistrictMaster() {
        return districtMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = districtMaster.getGrid().getSelectedRow();

        if (row != -1) {

            district = (District) districtMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = district.getId();

            // Reload from db
            district = districtService.find(pk);

//            // repopulate district picture
//            if (district.getPicture() != null) {
//                if (district.getPicture().getFile() != null) {
//                    district.setFile(district.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(district);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        District newDistrict = (District) newPersistentObject;

        // insert district picture
//        if (newDistrict.getFile() != null) {
//            newDistrict.setPicture(new Image());
//            newDistrict.getPicture().setFile(newDistrict.getFile());
//            imageService.saveImage(newDistrict.getPicture());
//        }

        districtService.save(newDistrict);

        return new VOResponse(newDistrict);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        District modifiedDistrict = (District) persistentObject;
        District oldDistrict = (District) oldPersistentObject;

//        if (oldDistrict.getPicture() != null) {
//
//            Image districtImage = imageService.findImage(
//                    oldDistrict.getPicture().getId());
//            
//            // update district picture
//            if (modifiedDistrict.getFile() != null) {
//
//                // update image
//                districtImage.setFile(modifiedDistrict.getFile());
//                imageService.updateImage(districtImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(districtImage);
//            }
//        } else {
//
//            // insert district picture
//            if (modifiedDistrict.getFile() != null) {
//                
//                modifiedDistrict.setPicture(new Image());
//                modifiedDistrict.getPicture().setFile(modifiedDistrict.getFile());
//                imageService.saveImage(modifiedDistrict.getPicture());
//            }
//        }



        districtService.update(district);

        return new VOResponse(modifiedDistrict);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        District deletedDistrict = (District) persistentObject;
        districtService.delete(district);

        return new VOResponse(deletedDistrict);
    }
}
