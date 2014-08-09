package com.mascova.solus.entity;

import com.mascova.solus.entity.Province;
import com.mascova.solus.dao.ProvinceDao;
import com.mascova.solus.service.ProvinceService;
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
public class ProvinceIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ProvinceDataOnDemand dod;

	@Autowired
    ProvinceService provinceService;

	@Autowired
    ProvinceDao provinceDao;

	@Test
    public void testCountAllProvinces() {
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", dod.getRandomProvince());
        long count = provinceService.countAll();
        Assert.assertTrue("Counter for 'Province' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindProvince() {
        Province obj = dod.getRandomProvince();
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Province' failed to provide an identifier", id);
        obj = provinceService.find(id);
        Assert.assertNotNull("Find method for 'Province' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Province' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllProvinces() {
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", dod.getRandomProvince());
        long count = provinceService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Province', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Province> result = provinceService.findAll();
        Assert.assertNotNull("Find all method for 'Province' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Province' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindProvinceEntries() {
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", dod.getRandomProvince());
        long count = provinceService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Province> result = provinceService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Province' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Province' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Province obj = dod.getRandomProvince();
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Province' failed to provide an identifier", id);
        obj = provinceService.find(id);
        Assert.assertNotNull("Find method for 'Province' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyProvince(obj);
        Integer currentVersion = obj.getVersion();
        provinceDao.flush();
        Assert.assertTrue("Version for 'Province' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateProvinceUpdate() {
        Province obj = dod.getRandomProvince();
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Province' failed to provide an identifier", id);
        obj = provinceService.find(id);
        boolean modified =  dod.modifyProvince(obj);
        Integer currentVersion = obj.getVersion();
        Province merged = provinceService.update(obj);
        provinceDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Province' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveProvince() {
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", dod.getRandomProvince());
        Province obj = dod.getNewTransientProvince(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Province' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Province' identifier to be null", obj.getId());
        provinceService.save(obj);
        provinceDao.flush();
        Assert.assertNotNull("Expected 'Province' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteProvince() {
        Province obj = dod.getRandomProvince();
        Assert.assertNotNull("Data on demand for 'Province' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Province' failed to provide an identifier", id);
        obj = provinceService.find(id);
        provinceService.delete(obj);
        provinceDao.flush();
        Assert.assertNull("Failed to remove 'Province' with identifier '" + id + "'", provinceService.find(id));
    }
}
