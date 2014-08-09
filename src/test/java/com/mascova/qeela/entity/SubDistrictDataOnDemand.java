package com.mascova.qeela.entity;

import com.mascova.qeela.entity.SubDistrict;
import com.mascova.qeela.entity.District;
import com.mascova.qeela.dao.SubDistrictDao;
import com.mascova.qeela.service.SubDistrictService;
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
public class SubDistrictDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<SubDistrict> data;

	@Autowired
    DistrictDataOnDemand districtDataOnDemand;

	@Autowired
    SubDistrictService subDistrictService;

	@Autowired
    SubDistrictDao subDistrictDao;

	public SubDistrict getNewTransientSubDistrict(int index) {
        SubDistrict obj = new SubDistrict();
        setCode(obj, index);
        setDistrict(obj, index);
        setName(obj, index);
        return obj;
    }

	public void setCode(SubDistrict obj, int index) {
        String code = "code_" + index;
        if (code.length() > 15) {
            code = code.substring(0, 15);
        }
        obj.setCode(code);
    }

	public void setDistrict(SubDistrict obj, int index) {
        District district = districtDataOnDemand.getRandomDistrict();
        obj.setDistrict(district);
    }

	public void setName(SubDistrict obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

	public SubDistrict getSpecificSubDistrict(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        SubDistrict obj = data.get(index);
        Long id = obj.getId();
        return subDistrictService.find(id);
    }

	public SubDistrict getRandomSubDistrict() {
        init();
        SubDistrict obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return subDistrictService.find(id);
    }

	public boolean modifySubDistrict(SubDistrict obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = subDistrictService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'SubDistrict' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<SubDistrict>();
        for (int i = 0; i < 10; i++) {
            SubDistrict obj = getNewTransientSubDistrict(i);
            try {
                subDistrictService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            subDistrictDao.flush();
            data.add(obj);
        }
    }
}
