package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Country;
import com.mascova.qeela.dao.CountryDao;
import com.mascova.qeela.service.CountryService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class CountryIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    CountryDataOnDemand dod;

	@Autowired
    CountryService countryService;

	@Autowired
    CountryDao countryDao;

	@Test
    public void testCountAllCountrys() {
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", dod.getRandomCountry());
        long count = countryService.countAll();
        Assert.assertTrue("Counter for 'Country' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCountry() {
        Country obj = dod.getRandomCountry();
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Country' failed to provide an identifier", id);
        obj = countryService.find(id);
        Assert.assertNotNull("Find method for 'Country' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Country' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllCountrys() {
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", dod.getRandomCountry());
        long count = countryService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Country', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Country> result = countryService.findAll();
        Assert.assertNotNull("Find all method for 'Country' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Country' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCountryEntries() {
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", dod.getRandomCountry());
        long count = countryService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Country> result = countryService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Country' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Country' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Country obj = dod.getRandomCountry();
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Country' failed to provide an identifier", id);
        obj = countryService.find(id);
        Assert.assertNotNull("Find method for 'Country' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCountry(obj);
        Integer currentVersion = obj.getVersion();
        countryDao.flush();
        Assert.assertTrue("Version for 'Country' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateCountryUpdate() {
        Country obj = dod.getRandomCountry();
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Country' failed to provide an identifier", id);
        obj = countryService.find(id);
        boolean modified =  dod.modifyCountry(obj);
        Integer currentVersion = obj.getVersion();
        Country merged = countryService.update(obj);
        countryDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Country' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveCountry() {
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", dod.getRandomCountry());
        Country obj = dod.getNewTransientCountry(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Country' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Country' identifier to be null", obj.getId());
        countryService.save(obj);
        countryDao.flush();
        Assert.assertNotNull("Expected 'Country' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteCountry() {
        Country obj = dod.getRandomCountry();
        Assert.assertNotNull("Data on demand for 'Country' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Country' failed to provide an identifier", id);
        obj = countryService.find(id);
        countryService.delete(obj);
        countryDao.flush();
        Assert.assertNull("Failed to remove 'Country' with identifier '" + id + "'", countryService.find(id));
    }
}
