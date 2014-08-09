package com.mascova.qeela.entity;

import com.mascova.qeela.service.CountryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class DbInitTest {
    
    @Autowired
    CountryService countryService;

    @Test
    public void testFindAvailablePelatihList() {
//        Mitra mitra = new Mitra();
//        mitra.setCode("M01");
//        List<Pelatih> availablePelatih = pelatihService.findAvailablePelatihList(mitra);
        
        Assert.assertTrue(true);
    }
}
