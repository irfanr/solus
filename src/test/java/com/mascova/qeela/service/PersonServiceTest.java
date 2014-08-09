package com.mascova.qeela.service;

import com.mascova.qeela.service.PersonService;
import com.mascova.qeela.entity.Person;
import java.util.Collections;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class PersonServiceTest {

    @Test
    public void testMarkerMethod() {
    }
    @Autowired
    PersonService personService;

    @Test
    public void testFindPersonEntries() {
        
//        List<Person> personList = personService.findPersonEntries(0, 10, null, SortOrder.UNSORTED, Collections.singletonMap("name2", "name2_1"));
        
//        System.out.println(personList.get(0).getName1());
        
        Person person = personService.save(new Person());
        
        Assert.assertTrue(person.getId()!=null);
        
    }

  
}
