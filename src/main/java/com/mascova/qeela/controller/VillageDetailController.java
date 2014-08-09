/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.service.ImageService;
import com.mascova.qeela.service.VillageService;
import com.mascova.qeela.ui.VillageDetail;
import com.mascova.qeela.ui.VillageMaster;
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
public class VillageDetailController extends FormController {

    private ApplicationContext ac = null;
    private VillageService villageService = null;
    private ImageService imageService = null;
    private VillageMaster villageMaster = null;
    private VillageDetail villageDetail = null;
    private long pk = -1;
    private Village village;
    private SubDistrictLookupController sdlc = null;

    public VillageDetailController(VillageMaster villageMaster, long pk,
            ApplicationContext ac) {
        this.villageMaster = villageMaster;
        this.pk = pk;
        this.ac = ac;

        villageService = ac.getBean(VillageService.class);
        imageService = ac.getBean(ImageService.class);
        sdlc = new SubDistrictLookupController(ac);

        villageDetail = new VillageDetail(this, sdlc);

        MDIFrame.add(villageDetail, true);

        if (pk != -1) {
            villageDetail.setTitle("Village Detail");
            villageDetail.getMainPanel().setMode(Consts.READONLY);
            villageDetail.getMainPanel().reload();

        } else {
            villageDetail.setTitle("Create Village");
            villageDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public VillageMaster getVillageMaster() {
        return villageMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = villageMaster.getGrid().getSelectedRow();

        if (row != -1) {

            village = (Village) villageMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = village.getId();

            // Reload from db
            village = villageService.find(pk);

//            // repopulate village picture
//            if (village.getPicture() != null) {
//                if (village.getPicture().getFile() != null) {
//                    village.setFile(village.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(village);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Village newVillage = (Village) newPersistentObject;

        // insert village picture
//        if (newVillage.getFile() != null) {
//            newVillage.setPicture(new Image());
//            newVillage.getPicture().setFile(newVillage.getFile());
//            imageService.saveImage(newVillage.getPicture());
//        }

        villageService.save(newVillage);

        return new VOResponse(newVillage);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Village modifiedVillage = (Village) persistentObject;
        Village oldVillage = (Village) oldPersistentObject;

//        if (oldVillage.getPicture() != null) {
//
//            Image villageImage = imageService.findImage(
//                    oldVillage.getPicture().getId());
//            
//            // update village picture
//            if (modifiedVillage.getFile() != null) {
//
//                // update image
//                villageImage.setFile(modifiedVillage.getFile());
//                imageService.updateImage(villageImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(villageImage);
//            }
//        } else {
//
//            // insert village picture
//            if (modifiedVillage.getFile() != null) {
//                
//                modifiedVillage.setPicture(new Image());
//                modifiedVillage.getPicture().setFile(modifiedVillage.getFile());
//                imageService.saveImage(modifiedVillage.getPicture());
//            }
//        }



        villageService.update(village);

        return new VOResponse(modifiedVillage);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Village deletedVillage = (Village) persistentObject;
        villageService.delete(village);

        return new VOResponse(deletedVillage);
    }
}
