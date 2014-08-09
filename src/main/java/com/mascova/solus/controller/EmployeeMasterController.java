/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Employee;
import com.mascova.solus.service.EmployeeService;
import com.mascova.solus.ui.EmployeeMaster;
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
public class EmployeeMasterController extends GridController implements GridDataLocator {

    private static final int BLOCK_SIZE = 10;
    private EmployeeMaster employeeMaster = null;
    private ApplicationContext ac = null;
    private EmployeeService employeeService = null;
    private VillageLookupController vlc = null;    

    public EmployeeMasterController(ApplicationContext ac) {
        this.ac = ac;
        employeeService = ac.getBean(EmployeeService.class);
        vlc = new VillageLookupController(ac);
        employeeMaster = new EmployeeMaster(this, vlc);
        employeeMaster.setTitle("Search Employee");
        MDIFrame.add(employeeMaster, true);

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

        String sql = "select p from Employee p ";
        Object[] paramValues = new Object[]{};
        try {

            int totalRecords = (int) employeeService.countAll();

            if (action == GridParams.PREVIOUS_BLOCK_ACTION) {
                startIndex -= BLOCK_SIZE;
            }
            List<Employee> employeeList = null;

            boolean moreRows = false;
            if (startIndex + BLOCK_SIZE >= totalRecords) {
                moreRows = false;
            } else {
                moreRows = true;
            }

            employeeList = employeeService.findEntries(action, startIndex, BLOCK_SIZE,
                    filteredColumns, currentSortedColumns, currentSortedVersusColumns,
                    valueObjectType, sql, paramValues);

            VOListResponse customVOLR = new VOListResponse(employeeList, moreRows, employeeList.size());

            return customVOLR;

        } catch (Exception ex) {
            Logger.getLogger(EmployeeMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) {

        List<Employee> insertedEmployeeList = employeeService.save(newValueObjects);

        return new VOListResponse(insertedEmployeeList, false, insertedEmployeeList.size());
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects,
            ArrayList persistentObjects) throws Exception {

        List<Employee> updatedEmployeeList = employeeService.update(persistentObjects);

        return new VOListResponse(updatedEmployeeList, false, updatedEmployeeList.size());
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {

        employeeService.delete(persistentObjects);

        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        Employee employee = (Employee) persistentObject;
//        JOptionPane.showMessageDialog(this.clientMaster, client.getName());
        new EmployeeDetailController(employeeMaster, employee.getId(), ac);
    }
}
