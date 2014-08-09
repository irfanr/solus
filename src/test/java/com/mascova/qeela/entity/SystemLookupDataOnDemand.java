package com.mascova.qeela.entity;

import com.mascova.qeela.entity.SystemLookup;
import com.mascova.qeela.dao.SystemLookupDao;
import com.mascova.qeela.service.SystemLookupService;
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
public class SystemLookupDataOnDemand {

    private Random rnd = new SecureRandom();
    private List<SystemLookup> data;
    @Autowired
    SystemLookupService systemLookupService;
    @Autowired
    SystemLookupDao systemLookupDao;

    public SystemLookup getNewTransientSystemLookup(int index) {
        SystemLookup obj = new SystemLookup();
        setType(obj, index);
        setCode(obj, index);
        setLiteral(obj, index);
        return obj;
    }

    public void setType(SystemLookup obj, int index) {
        String code = "type_" + index;
        if (code.length() > 8) {
            code = code.substring(0, 8);
        }
        obj.setType(code);
    }

    public void setCode(SystemLookup obj, int index) {
        String code = "code_" + index;
        if (code.length() > 8) {
            code = code.substring(0, 8);
        }
        obj.setCode(code);
    }

    public void setLiteral(SystemLookup obj, int index) {
        String code = "literal_" + index;
        if (code.length() > 128) {
            code = code.substring(0, 128);
        }
        obj.setLiteral(code);
    }

    public SystemLookup getSpecificSystemLookup(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        SystemLookup obj = data.get(index);
        Long id = obj.getId();
        return systemLookupService.find(id);
    }

    public SystemLookup getRandomSystemLookup() {
        init();
        SystemLookup obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return systemLookupService.find(id);
    }

    public boolean modifySystemLookup(SystemLookup obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = systemLookupService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'SystemLookup' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<SystemLookup>();
        for (int i = 0; i < 10; i++) {
            SystemLookup obj = getNewTransientSystemLookup(i);
            try {
                systemLookupService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            systemLookupDao.flush();
            data.add(obj);
        }
    }
}
