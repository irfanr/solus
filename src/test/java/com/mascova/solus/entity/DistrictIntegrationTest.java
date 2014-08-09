package com.mascova.solus.entity;

import com.mascova.solus.entity.District;
import com.mascova.solus.dao.DistrictDao;
import com.mascova.solus.service.DistrictService;
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
public class DistrictIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    DistrictDataOnDemand dod;

	@Autowired
    DistrictService districtService;

	@Autowired
    DistrictDao districtDao;

	@Test
    public void testCountAllDistricts() {
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        long count = districtService.countAll();
        Assert.assertTrue("Counter for 'District' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindDistrict() {
        District obj = dod.getRandomDistrict();
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        obj = districtService.find(id);
        Assert.assertNotNull("Find method for 'District' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'District' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllDistricts() {
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        long count = districtService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'District', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<District> result = districtService.findAll();
        Assert.assertNotNull("Find all method for 'District' illegally returned null", result);
        Assert.assertTrue("Find all method for 'District' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindDistrictEntries() {
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        long count = districtService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<District> result = districtService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'District' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'District' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        District obj = dod.getRandomDistrict();
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        obj = districtService.find(id);
        Assert.assertNotNull("Find method for 'District' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyDistrict(obj);
        Integer currentVersion = obj.getVersion();
        districtDao.flush();
        Assert.assertTrue("Version for 'District' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateDistrictUpdate() {
        District obj = dod.getRandomDistrict();
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        obj = districtService.find(id);
        boolean modified =  dod.modifyDistrict(obj);
        Integer currentVersion = obj.getVersion();
        District merged = districtService.update(obj);
        districtDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'District' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveDistrict() {
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", dod.getRandomDistrict());
        District obj = dod.getNewTransientDistrict(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'District' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'District' identifier to be null", obj.getId());
        districtService.save(obj);
        districtDao.flush();
        Assert.assertNotNull("Expected 'District' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteDistrict() {
        District obj = dod.getRandomDistrict();
        Assert.assertNotNull("Data on demand for 'District' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'District' failed to provide an identifier", id);
        obj = districtService.find(id);
        districtService.delete(obj);
        districtDao.flush();
        Assert.assertNull("Failed to remove 'District' with identifier '" + id + "'", districtService.find(id));
    }
}
