/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Province;
import com.mascova.solus.service.ImageService;
import com.mascova.solus.service.ProvinceService;
import com.mascova.solus.ui.ProvinceDetail;
import com.mascova.solus.ui.ProvinceMaster;
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
public class ProvinceDetailController extends FormController {

    private ApplicationContext ac = null;
    private ProvinceService provinceService = null;
    private ImageService imageService = null;
    private ProvinceMaster provinceMaster = null;
    private ProvinceDetail provinceDetail = null;
    private long pk = -1;
    private Province province;
    private CountryLookupController clc = null;

    public ProvinceDetailController(ProvinceMaster provinceMaster, long pk,
            ApplicationContext ac) {
        this.provinceMaster = provinceMaster;
        this.pk = pk;
        this.ac = ac;

        provinceService = ac.getBean(ProvinceService.class);
        imageService = ac.getBean(ImageService.class);
        clc = new CountryLookupController(ac);

        provinceDetail = new ProvinceDetail(this, clc);

        MDIFrame.add(provinceDetail, true);

        if (pk != -1) {
            provinceDetail.setTitle("Province Detail");
            provinceDetail.getMainPanel().setMode(Consts.READONLY);
            provinceDetail.getMainPanel().reload();

        } else {
            provinceDetail.setTitle("Create Province");
            provinceDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public ProvinceMaster getProvinceMaster() {
        return provinceMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = provinceMaster.getGrid().getSelectedRow();

        if (row != -1) {

            province = (Province) provinceMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = province.getId();

            // Reload from db
            province = provinceService.find(pk);

//            // repopulate province picture
//            if (province.getPicture() != null) {
//                if (province.getPicture().getFile() != null) {
//                    province.setFile(province.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(province);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Province newProvince = (Province) newPersistentObject;

        // insert province picture
//        if (newProvince.getFile() != null) {
//            newProvince.setPicture(new Image());
//            newProvince.getPicture().setFile(newProvince.getFile());
//            imageService.saveImage(newProvince.getPicture());
//        }

        provinceService.save(newProvince);

        return new VOResponse(newProvince);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Province modifiedProvince = (Province) persistentObject;
        Province oldProvince = (Province) oldPersistentObject;

//        if (oldProvince.getPicture() != null) {
//
//            Image provinceImage = imageService.findImage(
//                    oldProvince.getPicture().getId());
//            
//            // update province picture
//            if (modifiedProvince.getFile() != null) {
//
//                // update image
//                provinceImage.setFile(modifiedProvince.getFile());
//                imageService.updateImage(provinceImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(provinceImage);
//            }
//        } else {
//
//            // insert province picture
//            if (modifiedProvince.getFile() != null) {
//                
//                modifiedProvince.setPicture(new Image());
//                modifiedProvince.getPicture().setFile(modifiedProvince.getFile());
//                imageService.saveImage(modifiedProvince.getPicture());
//            }
//        }



        provinceService.update(province);

        return new VOResponse(modifiedProvince);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Province deletedProvince = (Province) persistentObject;
        provinceService.delete(province);

        return new VOResponse(deletedProvince);
    }
}
