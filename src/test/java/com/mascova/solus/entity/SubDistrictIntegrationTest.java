package com.mascova.solus.entity;

import com.mascova.solus.entity.SubDistrict;
import com.mascova.solus.dao.SubDistrictDao;
import com.mascova.solus.service.SubDistrictService;
import java.util.List;
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
public class SubDistrictIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    SubDistrictDataOnDemand dod;

	@Autowired
    SubDistrictService subDistrictService;

	@Autowired
    SubDistrictDao subDistrictDao;

	@Test
    public void testCountAllSubDistricts() {
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", dod.getRandomSubDistrict());
        long count = subDistrictService.countAll();
        Assert.assertTrue("Counter for 'SubDistrict' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindSubDistrict() {
        SubDistrict obj = dod.getRandomSubDistrict();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to provide an identifier", id);
        obj = subDistrictService.find(id);
        Assert.assertNotNull("Find method for 'SubDistrict' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SubDistrict' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllSubDistricts() {
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", dod.getRandomSubDistrict());
        long count = subDistrictService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'SubDistrict', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SubDistrict> result = subDistrictService.findAll();
        Assert.assertNotNull("Find all method for 'SubDistrict' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SubDistrict' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindSubDistrictEntries() {
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", dod.getRandomSubDistrict());
        long count = subDistrictService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<SubDistrict> result = subDistrictService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SubDistrict' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SubDistrict' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        SubDistrict obj = dod.getRandomSubDistrict();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to provide an identifier", id);
        obj = subDistrictService.find(id);
        Assert.assertNotNull("Find method for 'SubDistrict' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifySubDistrict(obj);
        Integer currentVersion = obj.getVersion();
        subDistrictDao.flush();
        Assert.assertTrue("Version for 'SubDistrict' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateSubDistrictUpdate() {
        SubDistrict obj = dod.getRandomSubDistrict();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to provide an identifier", id);
        obj = subDistrictService.find(id);
        boolean modified =  dod.modifySubDistrict(obj);
        Integer currentVersion = obj.getVersion();
        SubDistrict merged = subDistrictService.update(obj);
        subDistrictDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SubDistrict' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveSubDistrict() {
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", dod.getRandomSubDistrict());
        SubDistrict obj = dod.getNewTransientSubDistrict(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SubDistrict' identifier to be null", obj.getId());
        subDistrictService.save(obj);
        subDistrictDao.flush();
        Assert.assertNotNull("Expected 'SubDistrict' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteSubDistrict() {
        SubDistrict obj = dod.getRandomSubDistrict();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SubDistrict' failed to provide an identifier", id);
        obj = subDistrictService.find(id);
        subDistrictService.delete(obj);
        subDistrictDao.flush();
        Assert.assertNull("Failed to remove 'SubDistrict' with identifier '" + id + "'", subDistrictService.find(id));
    }
}
