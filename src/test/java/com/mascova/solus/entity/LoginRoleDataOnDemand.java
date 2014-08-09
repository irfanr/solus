package com.mascova.solus.entity;

import com.mascova.solus.entity.LoginRole;
import com.mascova.solus.dao.LoginRoleDao;
import com.mascova.solus.service.LoginRoleService;
import java.security.SecureRandom;
import java.util.ArrayList;
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
public class LoginRoleDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<LoginRole> data;

	@Autowired
    LoginRoleService loginRoleService;

	@Autowired
    LoginRoleDao loginRoleDao;

	public LoginRole getNewTransientLoginRole(int index) {
        LoginRole obj = new LoginRole();
        setCode(obj, index);
        setName(obj, index);
        return obj;
    }
        
	public void setCode(LoginRole obj, int index) {
        String code = "code_" + index;
        if (code.length() > 40) {
            code = code.substring(0, 40);
        }
        obj.setCode(code);
    }        

	public void setName(LoginRole obj, int index) {
        String name = "name_" + index;
        if (name.length() > 40) {
            name = name.substring(0, 40);
        }
        obj.setName(name);
    }

	public LoginRole getSpecificLoginRole(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        LoginRole obj = data.get(index);
        Long id = obj.getId();
        return loginRoleService.find(id);
    }

	public LoginRole getRandomLoginRole() {
        init();
        LoginRole obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return loginRoleService.find(id);
    }

	public boolean modifyLoginRole(LoginRole obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = loginRoleService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'LoginRole' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<LoginRole>();
        for (int i = 0; i < 10; i++) {
            LoginRole obj = getNewTransientLoginRole(i);
            try {
                loginRoleService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            loginRoleDao.flush();
            data.add(obj);
        }
    }
}
