package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Menu;
import com.mascova.qeela.dao.MenuDao;
import com.mascova.qeela.service.MenuService;
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

@Component
@Configurable
public class MenuDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Menu> data;

	@Autowired
    LinkDataOnDemand linkDataOnDemand;

	@Autowired
    MenuService menuService;

	@Autowired
    MenuDao menuDao;

	public Menu getNewTransientMenu(int index) {
        Menu obj = new Menu();
        setActive(obj, index);
        setName(obj, index);
        setParentMenu(obj, index);
        setSequence(obj, index);
        return obj;
    }

	public void setActive(Menu obj, int index) {
        Boolean active = Boolean.TRUE;
        obj.setActive(active);
    }

	public void setName(Menu obj, int index) {
        String name = "name_" + index;
        if (name.length() > 40) {
            name = name.substring(0, 40);
        }
        obj.setName(name);
    }

	public void setParentMenu(Menu obj, int index) {
        Menu parentMenu = obj;
        obj.setParentMenu(parentMenu);
    }

	public void setSequence(Menu obj, int index) {
        Integer sequence = new Integer(index);
        obj.setSequence(sequence);
    }

	public Menu getSpecificMenu(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Menu obj = data.get(index);
        Long id = obj.getId();
        return menuService.find(id);
    }

	public Menu getRandomMenu() {
        init();
        Menu obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return menuService.find(id);
    }

	public boolean modifyMenu(Menu obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = menuService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Menu' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Menu>();
        for (int i = 0; i < 10; i++) {
            Menu obj = getNewTransientMenu(i);
            try {
                menuService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            menuDao.flush();
            data.add(obj);
        }
    }
}
