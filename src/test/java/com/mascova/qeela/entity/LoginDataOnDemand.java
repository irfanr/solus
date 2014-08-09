package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Login;
import com.mascova.qeela.dao.LoginDao;
import com.mascova.qeela.service.LoginService;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class LoginDataOnDemand {

    private Random rnd = new SecureRandom();
    private List<Login> data;
    @Autowired
    PersonDataOnDemand personDataOnDemand;
    @Autowired
    LoginService loginService;
    @Autowired
    LoginDao loginDao;

    public Login getNewTransientLogin(int index) {
        Login obj = new Login();
        setCreated(obj, index);
        setModified(obj, index);
        setPassword(obj, index);
        setUsername(obj, index);
        return obj;
    }

    public void setCreated(Login obj, int index) {
        Date created = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreated(created);
    }

    public void setModified(Login obj, int index) {
        Date modified = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setModified(modified);
    }

    public void setPassword(Login obj, int index) {
        String password = "password_" + index;
        if (password.length() > 60) {
            password = password.substring(0, 60);
        }
        obj.setPassword(password);
    }

    public void setUsername(Login obj, int index) {
        String username = "username_" + index;
        if (username.length() > 25) {
            username = username.substring(0, 25);
        }
        obj.setUsername(username);
    }

    public Login getSpecificLogin(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Login obj = data.get(index);
        Long id = obj.getId();
        return loginService.find(id);
    }

    public Login getRandomLogin() {
        init();
        Login obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return loginService.find(id);
    }

    public boolean modifyLogin(Login obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = loginService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Login' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Login>();
        for (int i = 0; i < 10; i++) {
            Login obj = getNewTransientLogin(i);
            try {
                try {
                    loginService.saveAndEncryptPassword(obj);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginDataOnDemand.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            loginDao.flush();
            data.add(obj);
        }
    }
}
