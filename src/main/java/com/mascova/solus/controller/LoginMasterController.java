/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Login;
import com.mascova.solus.entity.Login;
import com.mascova.solus.service.LoginService;
import com.mascova.solus.service.LoginService;
import com.mascova.solus.ui.LoginMaster;
import com.mascova.solus.ui.LoginMaster;
import java.security.NoSuchAlgorithmException;
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
public class LoginMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private LoginMaster loginMaster = null;
    private ApplicationContext ac = null;
    private LoginService loginService = null;

    public LoginMasterController(ApplicationContext ac) {
        this.ac = ac;
        loginService = ac.getBean(LoginService.class);

        loginMaster = new LoginMaster(this);
        loginMaster.setTitle("Search Login");
        MDIFrame.add(loginMaster, true);

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

        String sql = "select l from Login l ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) loginService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Login> loginList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            loginList = loginService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(loginList, moreRows, loginList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(LoginMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {

        List<Login> insertedLoginList = loginService.update(newValueObjects);

        return new VOListResponse(insertedLoginList, false, insertedLoginList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Login> updatedLoginList = loginService.update(persistentObjects);

        return new VOListResponse(updatedLoginList, false, updatedLoginList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        loginService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Login login = (Login) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new LoginDetailController(loginMaster, login.getId(), ac);
    }    
}
