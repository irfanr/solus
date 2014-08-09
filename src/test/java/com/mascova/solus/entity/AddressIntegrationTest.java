package com.mascova.solus.entity;

import com.mascova.solus.entity.Address;
import com.mascova.solus.dao.AddressDao;
import com.mascova.solus.service.AddressService;
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
public class AddressIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    AddressDataOnDemand dod;

	@Autowired
    AddressService addressService;

	@Autowired
    AddressDao addressDao;

	@Test
    public void testCountAllAddresses() {
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", dod.getRandomAddress());
        long count = addressService.countAll();
        Assert.assertTrue("Counter for 'Address' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindAddress() {
        Address obj = dod.getRandomAddress();
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Address' failed to provide an identifier", id);
        obj = addressService.find(id);
        Assert.assertNotNull("Find method for 'Address' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Address' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllAddresses() {
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", dod.getRandomAddress());
        long count = addressService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Address', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Address> result = addressService.findAll();
        Assert.assertNotNull("Find all method for 'Address' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Address' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindAddressEntries() {
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", dod.getRandomAddress());
        long count = addressService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Address> result = addressService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Address' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Address' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Address obj = dod.getRandomAddress();
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Address' failed to provide an identifier", id);
        obj = addressService.find(id);
        Assert.assertNotNull("Find method for 'Address' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAddress(obj);
        Integer currentVersion = obj.getVersion();
        addressDao.flush();
        Assert.assertTrue("Version for 'Address' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateAddressUpdate() {
        Address obj = dod.getRandomAddress();
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Address' failed to provide an identifier", id);
        obj = addressService.find(id);
        boolean modified =  dod.modifyAddress(obj);
        Integer currentVersion = obj.getVersion();
        Address merged = addressService.update(obj);
        addressDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Address' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveAddress() {
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", dod.getRandomAddress());
        Address obj = dod.getNewTransientAddress(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Address' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Address' identifier to be null", obj.getId());
        addressService.save(obj);
        addressDao.flush();
        Assert.assertNotNull("Expected 'Address' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteAddress() {
        Address obj = dod.getRandomAddress();
        Assert.assertNotNull("Data on demand for 'Address' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Address' failed to provide an identifier", id);
        obj = addressService.find(id);
        addressService.delete(obj);
        addressDao.flush();
        Assert.assertNull("Failed to remove 'Address' with identifier '" + id + "'", addressService.find(id));
    }
}
