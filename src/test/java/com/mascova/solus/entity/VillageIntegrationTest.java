package com.mascova.solus.entity;

import com.mascova.solus.entity.Village;
import com.mascova.solus.dao.VillageDao;
import com.mascova.solus.service.VillageService;
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
public class VillageIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    VillageDataOnDemand dod;

	@Autowired
    VillageService villageService;

	@Autowired
    VillageDao villageDao;

	@Test
    public void testCountAllVillages() {
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", dod.getRandomVillage());
        long count = villageService.countAll();
        Assert.assertTrue("Counter for 'Village' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindVillage() {
        Village obj = dod.getRandomVillage();
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Village' failed to provide an identifier", id);
        obj = villageService.find(id);
        Assert.assertNotNull("Find method for 'Village' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Village' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllVillages() {
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", dod.getRandomVillage());
        long count = villageService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Village', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Village> result = villageService.findAll();
        Assert.assertNotNull("Find all method for 'Village' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Village' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindVillageEntries() {
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", dod.getRandomVillage());
        long count = villageService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Village> result = villageService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Village' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Village' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Village obj = dod.getRandomVillage();
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Village' failed to provide an identifier", id);
        obj = villageService.find(id);
        Assert.assertNotNull("Find method for 'Village' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyVillage(obj);
        Integer currentVersion = obj.getVersion();
        villageDao.flush();
        Assert.assertTrue("Version for 'Village' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateVillageUpdate() {
        Village obj = dod.getRandomVillage();
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Village' failed to provide an identifier", id);
        obj = villageService.find(id);
        boolean modified =  dod.modifyVillage(obj);
        Integer currentVersion = obj.getVersion();
        Village merged = villageService.update(obj);
        villageDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Village' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveVillage() {
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", dod.getRandomVillage());
        Village obj = dod.getNewTransientVillage(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Village' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Village' identifier to be null", obj.getId());
        villageService.save(obj);
        villageDao.flush();
        Assert.assertNotNull("Expected 'Village' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteVillage() {
        Village obj = dod.getRandomVillage();
        Assert.assertNotNull("Data on demand for 'Village' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Village' failed to provide an identifier", id);
        obj = villageService.find(id);
        villageService.delete(obj);
        villageDao.flush();
        Assert.assertNull("Failed to remove 'Village' with identifier '" + id + "'", villageService.find(id));
    }
}
