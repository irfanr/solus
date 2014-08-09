package com.mascova.solus.entity;

import com.mascova.solus.entity.Customer;
import com.mascova.solus.dao.CustomerDao;
import com.mascova.solus.service.CustomerService;
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
public class CustomerIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    CustomerDataOnDemand dod;

	@Autowired
    CustomerService customerService;

	@Autowired
    CustomerDao customerDao;

	@Test
    public void testCountAllPeople() {
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", dod.getRandomCustomer());
        long count = customerService.countAll();
        Assert.assertTrue("Counter for 'Customer' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindCustomer() {
        Customer obj = dod.getRandomCustomer();
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customer' failed to provide an identifier", id);
        obj = customerService.find(id);
        Assert.assertNotNull("Find method for 'Customer' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Customer' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllPeople() {
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", dod.getRandomCustomer());
        long count = customerService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Customer', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Customer> result = customerService.findAll();
        Assert.assertNotNull("Find all method for 'Customer' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Customer' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindCustomerEntries() {
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", dod.getRandomCustomer());
        long count = customerService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Customer> result = customerService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Customer' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Customer' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Customer obj = dod.getRandomCustomer();
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customer' failed to provide an identifier", id);
        obj = customerService.find(id);
        Assert.assertNotNull("Find method for 'Customer' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyCustomer(obj);
        Integer currentVersion = obj.getVersion();
        customerDao.flush();
        Assert.assertTrue("Version for 'Customer' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateCustomerUpdate() {
        Customer obj = dod.getRandomCustomer();
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customer' failed to provide an identifier", id);
        obj = customerService.find(id);
        boolean modified =  dod.modifyCustomer(obj);
        Integer currentVersion = obj.getVersion();
        Customer merged = customerService.update(obj);
        customerDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Customer' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveCustomer() {
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", dod.getRandomCustomer());
        Customer obj = dod.getNewTransientCustomer(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Customer' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Customer' identifier to be null", obj.getId());
        customerService.save(obj);
        customerDao.flush();
        Assert.assertNotNull("Expected 'Customer' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteCustomer() {
        Customer obj = dod.getRandomCustomer();
        Assert.assertNotNull("Data on demand for 'Customer' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Customer' failed to provide an identifier", id);
        obj = customerService.find(id);
        customerService.delete(obj);
        customerDao.flush();
        Assert.assertNull("Failed to remove 'Customer' with identifier '" + id + "'", customerService.find(id));
    }
}
