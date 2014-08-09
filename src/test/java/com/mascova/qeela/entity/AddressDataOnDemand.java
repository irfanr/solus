package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.entity.Person;
import com.mascova.qeela.entity.Address;
import com.mascova.qeela.dao.AddressDao;
import com.mascova.qeela.service.AddressService;
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
public class AddressDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Address> data;

	@Autowired
    PersonDataOnDemand personDataOnDemand;

	@Autowired
    VillageDataOnDemand villageDataOnDemand;

	@Autowired
    AddressService addressService;

	@Autowired
    AddressDao addressDao;

	public Address getNewTransientAddress(int index) {
        Address obj = new Address();
        setName(obj, index);
        setPerson(obj, index);
        setVillage(obj, index);
        return obj;
    }

	public void setName(Address obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

	public void setPerson(Address obj, int index) {
        Person person = personDataOnDemand.getRandomPerson();
        obj.setPerson(person);
    }

	public void setVillage(Address obj, int index) {
        Village village = villageDataOnDemand.getRandomVillage();
        obj.setVillage(village);
    }

	public Address getSpecificAddress(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Address obj = data.get(index);
        Long id = obj.getId();
        return addressService.find(id);
    }

	public Address getRandomAddress() {
        init();
        Address obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return addressService.find(id);
    }

	public boolean modifyAddress(Address obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = addressService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Address' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Address>();
        for (int i = 0; i < 10; i++) {
            Address obj = getNewTransientAddress(i);
            try {
                addressService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            addressDao.flush();
            data.add(obj);
        }
    }
}
