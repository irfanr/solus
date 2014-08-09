/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.controller;

import com.mascova.solus.entity.Employee;
import com.mascova.solus.service.EmployeeService;
import com.mascova.solus.ui.EmployeeDetail;
import com.mascova.solus.ui.EmployeeMaster;
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
public class EmployeeDetailController extends FormController {

    private ApplicationContext ac = null;
    private EmployeeService employeeService = null;
    private EmployeeMaster employeeMaster = null;
    private EmployeeDetail employeeDetail = null;
    private long pk = -1;
    private Employee employee;
    private VillageLookupController vlc = null;    

    public EmployeeDetailController(EmployeeMaster employeeMaster, long pk,
            ApplicationContext ac) {
        this.employeeMaster = employeeMaster;
        this.pk = pk;
        this.ac = ac;

        employeeService = ac.getBean(EmployeeService.class);
        vlc = new VillageLookupController(ac);        

        employeeDetail = new EmployeeDetail(this, vlc);

        MDIFrame.add(employeeDetail, true);

        if (pk != -1) {
            employeeDetail.setTitle("Employee Detail");
            employeeDetail.getMainPanel().setMode(Consts.READONLY);
            employeeDetail.getMainPanel().reload();

        } else {
            employeeDetail.setTitle("Create Employee");
            employeeDetail.getMainPanel().setMode(Consts.INSERT);

        }
    }

    public EmployeeMaster getEmployeeMaster() {
        return employeeMaster;
    }

    @Override
    public Response loadData(Class valueObjectClass) {

        int row = employeeMaster.getGrid().getSelectedRow();

        if (row != -1) {

            employee = (Employee) employeeMaster.getGrid().getVOListTableModel()
                    .getObjectForRow(row);
            pk = employee.getId();

            // Reload from db
            employee = employeeService.find(pk);

//            // repopulate employee picture
//            if (employee.getPicture() != null) {
//                if (employee.getPicture().getFile() != null) {
//                    employee.setFile(employee.getPicture().getFile());
//                }
//            }
        }

        return new VOResponse(employee);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {

        Employee newEmployee = (Employee) newPersistentObject;

        // insert employee picture
//        if (newEmployee.getFile() != null) {
//            newEmployee.setPicture(new Image());
//            newEmployee.getPicture().setFile(newEmployee.getFile());
//            imageService.saveImage(newEmployee.getPicture());
//        }

        employeeService.save(newEmployee);

        return new VOResponse(newEmployee);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {

        Employee modifiedEmployee = (Employee) persistentObject;
        Employee oldEmployee = (Employee) oldPersistentObject;

//        if (oldEmployee.getPicture() != null) {
//
//            Image employeeImage = imageService.findImage(
//                    oldEmployee.getPicture().getId());
//            
//            // update employee picture
//            if (modifiedEmployee.getFile() != null) {
//
//                // update image
//                employeeImage.setFile(modifiedEmployee.getFile());
//                imageService.updateImage(employeeImage);
//
//            } else {
//
//                // delete image
//                imageService.deleteImage(employeeImage);
//            }
//        } else {
//
//            // insert employee picture
//            if (modifiedEmployee.getFile() != null) {
//                
//                modifiedEmployee.setPicture(new Image());
//                modifiedEmployee.getPicture().setFile(modifiedEmployee.getFile());
//                imageService.saveImage(modifiedEmployee.getPicture());
//            }
//        }



        employeeService.update(employee);

        return new VOResponse(modifiedEmployee);
    }

    @Override
    public Response deleteRecord(ValueObject persistentObject) throws Exception {

        Employee deletedEmployee = (Employee) persistentObject;
        employeeService.delete(employee);

        return new VOResponse(deletedEmployee);
    }
}
