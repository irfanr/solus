package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.entity.Employee;
import com.mascova.qeela.dao.EmployeeDao;
import com.mascova.qeela.service.EmployeeService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class EmployeeDataOnDemand {

    private Random rnd = new SecureRandom();
    private List<Employee> data;
    @Autowired
    VillageDataOnDemand villageDataOnDemand;
    @Autowired
    LoginDataOnDemand loginDataOnDemand;    
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeDao employeeDao;

    public Employee getNewTransientEmployee(int index) {
        Employee obj = new Employee();
        setAddress(obj, index);
        setCreated(obj, index);
        setDob(obj, index);
        setGender(obj, index);
        setModified(obj, index);
        setMotherMaiden(obj, index);
        setName(obj, index);
        setTin(obj, index);
        setPassportNo(obj, index);
        setPicture(obj, index);
        setPlaceOfBirth(obj, index);
        setVillage(obj, index);
        setLogin(obj, index);
        return obj;
    }

    public void setAddress(Employee obj, int index) {
        String address = "address_" + index;
        if (address.length() > 80) {
            address = address.substring(0, 80);
        }
        obj.setAddress(address);
    }

    public void setCreated(Employee obj, int index) {
        Date created = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreated(created);
    }

    public void setDob(Employee obj, int index) {
        Date dob = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDob(dob);
    }

    public void setGender(Employee obj, int index) {
        String gender = String.valueOf(index);
        if (gender.length() > 1) {
            gender = gender.substring(0, 1);
        }
        obj.setGender(gender);
    }

    public void setModified(Employee obj, int index) {
        Date modified = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setModified(modified);
    }

    public void setMotherMaiden(Employee obj, int index) {
        String motherMaiden = "motherMaiden_" + index;
        if (motherMaiden.length() > 40) {
            motherMaiden = motherMaiden.substring(0, 40);
        }
        obj.setMotherMaiden(motherMaiden);
    }

    public void setName(Employee obj, int index) {
        String name = "name_" + index;
        if (name.length() > 20) {
            name = name.substring(0, 20);
        }
        obj.setName(name);
    }

    public void setTin(Employee obj, int index) {
        String tin = "tin" + index;
        if (tin.length() > 40) {
            tin = tin.substring(0, 40);
        }
        obj.setTin(tin);
    }

    public void setPassportNo(Employee obj, int index) {
        String passportNo = "passportNo_" + index;
        if (passportNo.length() > 40) {
            passportNo = passportNo.substring(0, 40);
        }
        obj.setPassportNo(passportNo);
    }

    public void setPicture(Employee obj, int index) {
        byte[] picture = String.valueOf(index).getBytes();
        obj.setPicture(picture);
    }

    public void setPlaceOfBirth(Employee obj, int index) {
        String placeOfBirth = "placeOfBirth_" + index;
        if (placeOfBirth.length() > 20) {
            placeOfBirth = placeOfBirth.substring(0, 20);
        }
        obj.setPlaceOfBirth(placeOfBirth);
    }

    public void setVillage(Employee obj, int index) {
        Village village = villageDataOnDemand.getRandomVillage();
        obj.setVillage(village);
    }
    
    public void setLogin(Employee obj, int index) {
        Login login = loginDataOnDemand.getRandomLogin();
        obj.setLogin(login);
    }    

    public Employee getSpecificEmployee(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Employee obj = data.get(index);
        Long id = obj.getId();
        return employeeService.find(id);
    }

    public Employee getRandomEmployee() {
        init();
        Employee obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return employeeService.find(id);
    }

    public boolean modifyEmployee(Employee obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = employeeService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Employee' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Employee>();
        for (int i = 0; i < 10; i++) {
            Employee obj = getNewTransientEmployee(i);
            try {
                employeeService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            employeeDao.flush();
            data.add(obj);
        }
    }
}
