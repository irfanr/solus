package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.entity.Person;
import com.mascova.qeela.entity.Service;
import com.mascova.qeela.dao.ServiceDao;
import com.mascova.qeela.service.ServiceService;
import java.math.BigDecimal;
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
public class ServiceDataOnDemand {

    private Random rnd = new SecureRandom();
    private List<Service> data;
    @Autowired
    ServiceService serviceService;
    @Autowired
    ServiceDao serviceDao;

    public Service getNewTransientService(int index) {
        Service obj = new Service();
        setName(obj, index);
        setPrice(obj, index);
        return obj;
    }

    public void setName(Service obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

    private void setPrice(Service obj, int index) {
        obj.setPrice( new BigDecimal(rnd.nextDouble()) );
    }

    public Service getSpecificService(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Service obj = data.get(index);
        Long id = obj.getId();
        return serviceService.find(id);
    }

    public Service getRandomService() {
        init();
        Service obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return serviceService.find(id);
    }

    public boolean modifyService(Service obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = serviceService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Service' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Service>();
        for (int i = 0; i < 10; i++) {
            Service obj = getNewTransientService(i);
            try {
                serviceService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            serviceDao.flush();
            data.add(obj);
        }
    }
}
