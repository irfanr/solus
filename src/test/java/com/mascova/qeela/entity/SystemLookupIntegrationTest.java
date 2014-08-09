package com.mascova.qeela.entity;

import com.mascova.qeela.entity.SystemLookup;
import com.mascova.qeela.dao.SystemLookupDao;
import com.mascova.qeela.service.SystemLookupService;
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
public class SystemLookupIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
    @Autowired
    SystemLookupDataOnDemand dod;
    @Autowired
    SystemLookupService systemLookupService;
    @Autowired
    SystemLookupDao systemLookupDao;

    @Test
    public void testCountAllSystemLookups() {
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", dod.getRandomSystemLookup());
        long count = systemLookupService.countAll();
        Assert.assertTrue("Counter for 'SystemLookup' incorrectly reported there were no entries", count > 0);
    }

    @Test
    public void testFindSystemLookup() {
        SystemLookup obj = dod.getRandomSystemLookup();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to provide an identifier", id);
        obj = systemLookupService.find(id);
        Assert.assertNotNull("Find method for 'SystemLookup' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'SystemLookup' returned the incorrect identifier", id, obj.getId());
    }

    @Test
    public void testFindAllSystemLookups() {
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", dod.getRandomSystemLookup());
        long count = systemLookupService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'SystemLookup', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<SystemLookup> result = systemLookupService.findAll();
        Assert.assertNotNull("Find all method for 'SystemLookup' illegally returned null", result);
        Assert.assertTrue("Find all method for 'SystemLookup' failed to return any data", result.size() > 0);
    }

    @Test
    public void testFindSystemLookupEntries() {
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", dod.getRandomSystemLookup());
        long count = systemLookupService.countAll();
        if (count > 20) {
            count = 20;
        }
        int firstResult = 0;
        int maxResults = (int) count;
        List<SystemLookup> result = systemLookupService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'SystemLookup' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'SystemLookup' returned an incorrect number of entries", count, result.size());
    }

    @Test
    public void testFlush() {
        SystemLookup obj = dod.getRandomSystemLookup();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to provide an identifier", id);
        obj = systemLookupService.find(id);
        Assert.assertNotNull("Find method for 'SystemLookup' illegally returned null for id '" + id + "'", obj);
        boolean modified = dod.modifySystemLookup(obj);
        Integer currentVersion = obj.getVersion();
        systemLookupDao.flush();
        Assert.assertTrue("Version for 'SystemLookup' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testUpdateSystemLookupUpdate() {
        SystemLookup obj = dod.getRandomSystemLookup();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to provide an identifier", id);
        obj = systemLookupService.find(id);
        boolean modified = dod.modifySystemLookup(obj);
        Integer currentVersion = obj.getVersion();
        SystemLookup merged = systemLookupService.update(obj);
        systemLookupDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'SystemLookup' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testSaveSystemLookup() {
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", dod.getRandomSystemLookup());
        SystemLookup obj = dod.getNewTransientSystemLookup(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'SystemLookup' identifier to be null", obj.getId());
        systemLookupService.save(obj);
        systemLookupDao.flush();
        Assert.assertNotNull("Expected 'SystemLookup' identifier to no longer be null", obj.getId());
    }

    @Test
    public void testDeleteSystemLookup() {
        SystemLookup obj = dod.getRandomSystemLookup();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'SystemLookup' failed to provide an identifier", id);
        obj = systemLookupService.find(id);
        systemLookupService.delete(obj);
        systemLookupDao.flush();
        Assert.assertNull("Failed to remove 'SystemLookup' with identifier '" + id + "'", systemLookupService.find(id));
    }
}
