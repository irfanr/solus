package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Service;
import com.mascova.qeela.dao.ServiceDao;
import com.mascova.qeela.service.ServiceService;
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
public class ServiceIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
    @Autowired
    ServiceDataOnDemand dod;
    @Autowired
    ServiceService serviceService;
    @Autowired
    ServiceDao serviceDao;

    @Test
    public void testCountAllServicees() {
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", dod.getRandomService());
        long count = serviceService.countAll();
        Assert.assertTrue("Counter for 'Service' incorrectly reported there were no entries", count > 0);
    }

    @Test
    public void testFindService() {
        Service obj = dod.getRandomService();
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Service' failed to provide an identifier", id);
        obj = serviceService.find(id);
        Assert.assertNotNull("Find method for 'Service' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Service' returned the incorrect identifier", id, obj.getId());
    }

    @Test
    public void testFindAllServices() {
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", dod.getRandomService());
        long count = serviceService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Service', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Service> result = serviceService.findAll();
        Assert.assertNotNull("Find all method for 'Service' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Service' failed to return any data", result.size() > 0);
    }

    @Test
    public void testFindServiceEntries() {
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", dod.getRandomService());
        long count = serviceService.countAll();
        if (count > 20) {
            count = 20;
        }
        int firstResult = 0;
        int maxResults = (int) count;
        List<Service> result = serviceService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Service' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Service' returned an incorrect number of entries", count, result.size());
    }

    @Test
    public void testFlush() {
        Service obj = dod.getRandomService();
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Service' failed to provide an identifier", id);
        obj = serviceService.find(id);
        Assert.assertNotNull("Find method for 'Service' illegally returned null for id '" + id + "'", obj);
        boolean modified = dod.modifyService(obj);
        Integer currentVersion = obj.getVersion();
        serviceDao.flush();
        Assert.assertTrue("Version for 'Service' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testUpdateServiceUpdate() {
        Service obj = dod.getRandomService();
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Service' failed to provide an identifier", id);
        obj = serviceService.find(id);
        boolean modified = dod.modifyService(obj);
        Integer currentVersion = obj.getVersion();
        Service merged = serviceService.update(obj);
        serviceDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Service' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testSaveService() {
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", dod.getRandomService());
        Service obj = dod.getNewTransientService(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Service' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Service' identifier to be null", obj.getId());
        serviceService.save(obj);
        serviceDao.flush();
        Assert.assertNotNull("Expected 'Service' identifier to no longer be null", obj.getId());
    }

    @Test
    public void testDeleteService() {
        Service obj = dod.getRandomService();
        Assert.assertNotNull("Data on demand for 'Service' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Service' failed to provide an identifier", id);
        obj = serviceService.find(id);
        serviceService.delete(obj);
        serviceDao.flush();
        Assert.assertNull("Failed to remove 'Service' with identifier '" + id + "'", serviceService.find(id));
    }
}
