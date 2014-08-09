package com.mascova.solus.entity;

import com.mascova.solus.entity.Login;
import com.mascova.solus.dao.LoginDao;
import com.mascova.solus.service.LoginService;
import java.security.NoSuchAlgorithmException;
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
public class LoginIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    LoginDataOnDemand dod;

	@Autowired
    LoginService loginService;

	@Autowired
    LoginDao loginDao;

	@Test
    public void testCountAllLogins() {
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", dod.getRandomLogin());
        long count = loginService.countAll();
        Assert.assertTrue("Counter for 'Login' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindLogin() {
        Login obj = dod.getRandomLogin();
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Login' failed to provide an identifier", id);
        obj = loginService.find(id);
        Assert.assertNotNull("Find method for 'Login' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Login' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllLogins() {
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", dod.getRandomLogin());
        long count = loginService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Login', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Login> result = loginService.findAll();
        Assert.assertNotNull("Find all method for 'Login' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Login' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindLoginEntries() {
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", dod.getRandomLogin());
        long count = loginService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Login> result = loginService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Login' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Login' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Login obj = dod.getRandomLogin();
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Login' failed to provide an identifier", id);
        obj = loginService.find(id);
        Assert.assertNotNull("Find method for 'Login' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLogin(obj);
        Integer currentVersion = obj.getVersion();
        loginDao.flush();
        Assert.assertTrue("Version for 'Login' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateLoginUpdate() throws NoSuchAlgorithmException {
        Login obj = dod.getRandomLogin();
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Login' failed to provide an identifier", id);
        obj = loginService.find(id);
        boolean modified =  dod.modifyLogin(obj);
        Integer currentVersion = obj.getVersion();
        Login merged = loginService.updateAndEncryptPassword(obj);
        loginDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Login' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveLogin() throws NoSuchAlgorithmException {
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", dod.getRandomLogin());
        Login obj = dod.getNewTransientLogin(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Login' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Login' identifier to be null", obj.getId());
        loginService.saveAndEncryptPassword(obj);
        loginDao.flush();
        Assert.assertNotNull("Expected 'Login' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteLogin() {
        Login obj = dod.getRandomLogin();
        Assert.assertNotNull("Data on demand for 'Login' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Login' failed to provide an identifier", id);
        obj = loginService.find(id);
        loginService.delete(obj);
        loginDao.flush();
        Assert.assertNull("Failed to remove 'Login' with identifier '" + id + "'", loginService.find(id));
    }
}
