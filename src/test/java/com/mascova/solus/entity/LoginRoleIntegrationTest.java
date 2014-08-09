package com.mascova.solus.entity;

import com.mascova.solus.entity.LoginRole;
import com.mascova.solus.dao.LoginRoleDao;
import com.mascova.solus.service.LoginRoleService;
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
public class LoginRoleIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }
    @Autowired
    LoginRoleDataOnDemand dod;
    @Autowired
    LoginRoleService loginRoleService;
    @Autowired
    LoginRoleDao loginRoleDao;

    @Test
    public void testCountAllLoginRoles() {
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", dod.getRandomLoginRole());
        long count = loginRoleService.countAll();
        Assert.assertTrue("Counter for 'LoginRole' incorrectly reported there were no entries", count > 0);
    }

    @Test
    public void testFindLoginRole() {
        LoginRole obj = dod.getRandomLoginRole();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to provide an identifier", id);
        obj = loginRoleService.find(id);
        Assert.assertNotNull("Find method for 'LoginRole' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'LoginRole' returned the incorrect identifier", id, obj.getId());
    }

    @Test
    public void testFindAllLoginRoles() {
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", dod.getRandomLoginRole());
        long count = loginRoleService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'LoginRole', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<LoginRole> result = loginRoleService.findAll();
        Assert.assertNotNull("Find all method for 'LoginRole' illegally returned null", result);
        Assert.assertTrue("Find all method for 'LoginRole' failed to return any data", result.size() > 0);
    }

    @Test
    public void testFindLoginRoleEntries() {
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", dod.getRandomLoginRole());
        long count = loginRoleService.countAll();
        if (count > 20) {
            count = 20;
        }
        int firstResult = 0;
        int maxResults = (int) count;
        List<LoginRole> result = loginRoleService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'LoginRole' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'LoginRole' returned an incorrect number of entries", count, result.size());
    }

    @Test
    public void testFlush() {
        LoginRole obj = dod.getRandomLoginRole();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to provide an identifier", id);
        obj = loginRoleService.find(id);
        Assert.assertNotNull("Find method for 'LoginRole' illegally returned null for id '" + id + "'", obj);
        boolean modified = dod.modifyLoginRole(obj);
        Integer currentVersion = obj.getVersion();
        loginRoleDao.flush();
        Assert.assertTrue("Version for 'LoginRole' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testUpdateLoginRoleUpdate() {
        LoginRole obj = dod.getRandomLoginRole();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to provide an identifier", id);
        obj = loginRoleService.find(id);
        boolean modified = dod.modifyLoginRole(obj);
        Integer currentVersion = obj.getVersion();
        LoginRole merged = loginRoleService.update(obj);
        loginRoleDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'LoginRole' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

    @Test
    public void testSaveLoginRole() {
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", dod.getRandomLoginRole());
        LoginRole obj = dod.getNewTransientLoginRole(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'LoginRole' identifier to be null", obj.getId());
        loginRoleService.save(obj);
        loginRoleDao.flush();
        Assert.assertNotNull("Expected 'LoginRole' identifier to no longer be null", obj.getId());
    }

    @Test
    public void testDeleteLoginRole() {
        LoginRole obj = dod.getRandomLoginRole();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'LoginRole' failed to provide an identifier", id);
        obj = loginRoleService.find(id);
        loginRoleService.delete(obj);
        loginRoleDao.flush();
        Assert.assertNull("Failed to remove 'LoginRole' with identifier '" + id + "'", loginRoleService.find(id));
    }
}
