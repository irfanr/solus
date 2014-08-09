package com.mascova.solus.entity;

import com.mascova.solus.entity.SubDistrict;
import com.mascova.solus.entity.Village;
import com.mascova.solus.dao.VillageDao;
import com.mascova.solus.service.VillageService;
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
public class VillageDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Village> data;

	@Autowired
    SubDistrictDataOnDemand subDistrictDataOnDemand;

	@Autowired
    VillageService villageService;

	@Autowired
    VillageDao villageDao;

	public Village getNewTransientVillage(int index) {
        Village obj = new Village();
        setCode(obj, index);
        setName(obj, index);
        setSubDistrict(obj, index);
        return obj;
    }

	public void setCode(Village obj, int index) {
        String code = "code_" + index;
        if (code.length() > 15) {
            code = code.substring(0, 15);
        }
        obj.setCode(code);
    }

	public void setName(Village obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

	public void setSubDistrict(Village obj, int index) {
        SubDistrict subDistrict = subDistrictDataOnDemand.getRandomSubDistrict();
        obj.setSubDistrict(subDistrict);
    }

	public Village getSpecificVillage(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Village obj = data.get(index);
        Long id = obj.getId();
        return villageService.find(id);
    }

	public Village getRandomVillage() {
        init();
        Village obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return villageService.find(id);
    }

	public boolean modifyVillage(Village obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = villageService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Village' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Village>();
        for (int i = 0; i < 10; i++) {
            Village obj = getNewTransientVillage(i);
            try {
                villageService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            villageDao.flush();
            data.add(obj);
        }
    }
}
