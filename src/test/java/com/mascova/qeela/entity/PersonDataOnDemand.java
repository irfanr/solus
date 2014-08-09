package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.entity.Person;
import com.mascova.qeela.dao.PersonDao;
import com.mascova.qeela.service.PersonService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class PersonDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Person> data;

	@Autowired
    VillageDataOnDemand villageDataOnDemand;

	@Autowired
    PersonService personService;

	@Autowired
    PersonDao personDao;

	public Person getNewTransientPerson(int index) {
        Person obj = new Person();
        setAddress(obj, index);
        setCreated(obj, index);
        setDob(obj, index);
        setGender(obj, index);
        setModified(obj, index);
        setMotherMaiden(obj, index);
        setName1(obj, index);
        setName2(obj, index);
        setName3(obj, index);
        setName4(obj, index);
        setNpwp(obj, index);
        setPassportNo(obj, index);
        setPicture(obj, index);
        setPlaceOfBirth(obj, index);
        setVillage(obj, index);
        return obj;
    }

	public void setAddress(Person obj, int index) {
        String address = "address_" + index;
        if (address.length() > 80) {
            address = address.substring(0, 80);
        }
        obj.setAddress(address);
    }

	public void setCreated(Person obj, int index) {
        Date created = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreated(created);
    }

	public void setDob(Person obj, int index) {
        Date dob = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDob(dob);
    }

	public void setGender(Person obj, int index) {
        String gender = String.valueOf(index);
        if (gender.length() > 1) {
            gender = gender.substring(0, 1);
        }
        obj.setGender(gender);
    }

	public void setModified(Person obj, int index) {
        Date modified = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setModified(modified);
    }

	public void setMotherMaiden(Person obj, int index) {
        String motherMaiden = "motherMaiden_" + index;
        if (motherMaiden.length() > 40) {
            motherMaiden = motherMaiden.substring(0, 40);
        }
        obj.setMotherMaiden(motherMaiden);
    }

	public void setName1(Person obj, int index) {
        String name1 = "name1_" + index;
        if (name1.length() > 20) {
            name1 = name1.substring(0, 20);
        }
        obj.setName1(name1);
    }

	public void setName2(Person obj, int index) {
        String name2 = "name2_" + index;
        if (name2.length() > 20) {
            name2 = name2.substring(0, 20);
        }
        obj.setName2(name2);
    }

	public void setName3(Person obj, int index) {
        String name3 = "name3_" + index;
        if (name3.length() > 20) {
            name3 = name3.substring(0, 20);
        }
        obj.setName3(name3);
    }

	public void setName4(Person obj, int index) {
        String name4 = "name4_" + index;
        if (name4.length() > 20) {
            name4 = name4.substring(0, 20);
        }
        obj.setName4(name4);
    }

	public void setNpwp(Person obj, int index) {
        String npwp = "npwp_" + index;
        if (npwp.length() > 40) {
            npwp = npwp.substring(0, 40);
        }
        obj.setNpwp(npwp);
    }

	public void setPassportNo(Person obj, int index) {
        String passportNo = "passportNo_" + index;
        if (passportNo.length() > 40) {
            passportNo = passportNo.substring(0, 40);
        }
        obj.setPassportNo(passportNo);
    }

	public void setPicture(Person obj, int index) {
        byte[] picture = String.valueOf(index).getBytes();
        obj.setPicture(picture);
    }

	public void setPlaceOfBirth(Person obj, int index) {
        String placeOfBirth = "placeOfBirth_" + index;
        if (placeOfBirth.length() > 20) {
            placeOfBirth = placeOfBirth.substring(0, 20);
        }
        obj.setPlaceOfBirth(placeOfBirth);
    }

	public void setVillage(Person obj, int index) {
        Village village = villageDataOnDemand.getRandomVillage();
        obj.setVillage(village);
    }

	public Person getSpecificPerson(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Person obj = data.get(index);
        Long id = obj.getId();
        return personService.find(id);
    }

	public Person getRandomPerson() {
        init();
        Person obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return personService.find(id);
    }

	public boolean modifyPerson(Person obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = personService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Person' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person obj = getNewTransientPerson(i);
            try {
                personService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            personDao.flush();
            data.add(obj);
        }
    }
}
