package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Country;
import com.mascova.qeela.entity.Province;
import com.mascova.qeela.dao.ProvinceDao;
import com.mascova.qeela.service.ProvinceService;
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
public class ProvinceDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Province> data;

	@Autowired
    CountryDataOnDemand countryDataOnDemand;

	@Autowired
    ProvinceService provinceService;

	@Autowired
    ProvinceDao provinceDao;

	public Province getNewTransientProvince(int index) {
        Province obj = new Province();
        setCode(obj, index);
        setCountry(obj, index);
        setName(obj, index);
        return obj;
    }

	public void setCode(Province obj, int index) {
        String code = "code_" + index;
        if (code.length() > 15) {
            code = code.substring(0, 15);
        }
        obj.setCode(code);
    }

	public void setCountry(Province obj, int index) {
        Country country = countryDataOnDemand.getRandomCountry();
        obj.setCountry(country);
    }

	public void setName(Province obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

	public Province getSpecificProvince(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Province obj = data.get(index);
        Long id = obj.getId();
        return provinceService.find(id);
    }

	public Province getRandomProvince() {
        init();
        Province obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return provinceService.find(id);
    }

	public boolean modifyProvince(Province obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = provinceService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Province' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Province>();
        for (int i = 0; i < 10; i++) {
            Province obj = getNewTransientProvince(i);
            try {
                provinceService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            provinceDao.flush();
            data.add(obj);
        }
    }
}
