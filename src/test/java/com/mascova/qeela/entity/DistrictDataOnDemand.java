package com.mascova.qeela.entity;

import com.mascova.qeela.entity.District;
import com.mascova.qeela.entity.Province;
import com.mascova.qeela.dao.DistrictDao;
import com.mascova.qeela.service.DistrictService;
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
public class DistrictDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<District> data;

	@Autowired
    ProvinceDataOnDemand provinceDataOnDemand;

	@Autowired
    DistrictService districtService;

	@Autowired
    DistrictDao districtDao;

	public District getNewTransientDistrict(int index) {
        District obj = new District();
        setCode(obj, index);
        setName(obj, index);
        setProvince(obj, index);
        return obj;
    }

	public void setCode(District obj, int index) {
        String code = "code_" + index;
        if (code.length() > 15) {
            code = code.substring(0, 15);
        }
        obj.setCode(code);
    }

	public void setName(District obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

	public void setProvince(District obj, int index) {
        Province province = provinceDataOnDemand.getRandomProvince();
        obj.setProvince(province);
    }

	public District getSpecificDistrict(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        District obj = data.get(index);
        Long id = obj.getId();
        return districtService.find(id);
    }

	public District getRandomDistrict() {
        init();
        District obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return districtService.find(id);
    }

	public boolean modifyDistrict(District obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = districtService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'District' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<District>();
        for (int i = 0; i < 10; i++) {
            District obj = getNewTransientDistrict(i);
            try {
                districtService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            districtDao.flush();
            data.add(obj);
        }
    }
}
