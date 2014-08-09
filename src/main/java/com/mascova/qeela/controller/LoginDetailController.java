/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.controller;

import com.mascova.qeela.entity.Login;
import com.mascova.qeela.service.ImageService;
import com.mascova.qeela.service.LoginService;
import com.mascova.qeela.ui.LoginDetail;
import com.mascova.qeela.ui.LoginMaster;
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
public class LoginDetailController extends FormController {

    private ApplicationContext ac = null;
    private LoginService loginService = null;
    private LoginMaster loginMaster = null;
    private LoginDetail loginDetail = null;
    private long pk = -1;
    private Login login;

    public LoginDetailController(LoginMaster loginMaster, long pk,
            ApplicationContext ac) {
        this.loginMaster = loginMaster;
        this.pk = pk;
        this.ac = ac;

        loginService = ac.getBean(LoginService.class);

        loginDetail = new LoginDetail(this);

        MDIFrame.add(loginDetail, true);

        if (pk != -1) {
            loginDetail.setTitle("Login Detail");
            loginDetail.getMainPanel().setMode(Consts.READONLY);
            loginDetail.getMainPanel().reload();

        } else {
            loginDetail.setTitle("Create Login");
            loginDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public LoginMaster getLoginMaster() {
        return loginMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = loginMaster.getGrid().getSelectedRow();

        if (row != -1) {

            login = (Login) loginMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = login.getId();

            // Reload from db
            login = loginService.find(pk);

//            // repopulate login picture
//            if (login.getPicture() != null) {
//                if (login.getPicture().getFile() != null) {
//                    login.setFile(login.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(login);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Login newLogin = (Login) newPersistentObject;

        // insert login picture
//        if (newLogin.getFile() != null) {
//            newLogin.setPicture(new Image());
//            newLogin.getPicture().setFile(newLogin.getFile());
//            imageService.saveImage(newLogin.getPicture());
//        }

        loginService.save(newLogin);

        return new VOResponse(newLogin);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Login modifiedLogin = (Login) persistentObject;
        Login oldLogin = (Login) oldPersistentObject;

//        if (oldLogin.getPicture() != null) {
//
//            Image loginImage = imageService.findImage(
//                    oldLogin.getPicture().getId());
//            
//            // update login picture
//            if (modifiedLogin.getFile() != null) {
//
//                // update image
//                loginImage.setFile(modifiedLogin.getFile());
//                imageService.updateImage(loginImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(loginImage);
//            }
//        } else {
//
//            // insert login picture
//            if (modifiedLogin.getFile() != null) {
//                
//                modifiedLogin.setPicture(new Image());
//                modifiedLogin.getPicture().setFile(modifiedLogin.getFile());
//                imageService.saveImage(modifiedLogin.getPicture());
//            }
//        }



        loginService.update(login);

        return new VOResponse(modifiedLogin);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Login deletedLogin = (Login) persistentObject;
        loginService.delete(login);

        loginMaster.getGrid().reloadData();
        loginDetail.closeFrame();

        return new VOResponse(deletedLogin);
    }
}
