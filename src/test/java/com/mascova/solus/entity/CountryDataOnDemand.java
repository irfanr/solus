package com.mascova.solus.entity;

import com.mascova.solus.entity.Country;
import com.mascova.solus.dao.CountryDao;
import com.mascova.solus.service.CountryService;
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
public class CountryDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Country> data;

	@Autowired
    CountryService countryService;

	@Autowired
    CountryDao countryDao;

	public Country getNewTransientCountry(int index) {
        Country obj = new Country();
        setCode(obj, index);
        setName(obj, index);
        return obj;
    }

	public void setCode(Country obj, int index) {
        String code = "code_" + index;
        if (code.length() > 15) {
            code = code.substring(0, 15);
        }
        obj.setCode(code);
    }

	public void setName(Country obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

	public Country getSpecificCountry(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Country obj = data.get(index);
        Long id = obj.getId();
        return countryService.find(id);
    }

	public Country getRandomCountry() {
        init();
        Country obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return countryService.find(id);
    }

	public boolean modifyCountry(Country obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = countryService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Country' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Country>();
        for (int i = 0; i < 10; i++) {
            Country obj = getNewTransientCountry(i);
            try {
                countryService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            countryDao.flush();
            data.add(obj);
        }
    }
}
