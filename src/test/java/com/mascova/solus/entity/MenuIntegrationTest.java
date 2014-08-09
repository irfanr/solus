package com.mascova.solus.entity;

import com.mascova.solus.entity.Menu;
import com.mascova.solus.dao.MenuDao;
import com.mascova.solus.service.MenuService;
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
public class MenuIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    MenuDataOnDemand dod;

	@Autowired
    MenuService menuService;

	@Autowired
    MenuDao menuDao;

	@Test
    public void testCountAllMenus() {
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", dod.getRandomMenu());
        long count = menuService.countAll();
        Assert.assertTrue("Counter for 'Menu' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindMenu() {
        Menu obj = dod.getRandomMenu();
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Menu' failed to provide an identifier", id);
        obj = menuService.find(id);
        Assert.assertNotNull("Find method for 'Menu' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Menu' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllMenus() {
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", dod.getRandomMenu());
        long count = menuService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Menu', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Menu> result = menuService.findAll();
        Assert.assertNotNull("Find all method for 'Menu' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Menu' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindMenuEntries() {
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", dod.getRandomMenu());
        long count = menuService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Menu> result = menuService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Menu' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Menu' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Menu obj = dod.getRandomMenu();
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Menu' failed to provide an identifier", id);
        obj = menuService.find(id);
        Assert.assertNotNull("Find method for 'Menu' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyMenu(obj);
        Integer currentVersion = obj.getVersion();
        menuDao.flush();
        Assert.assertTrue("Version for 'Menu' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateMenuUpdate() {
        Menu obj = dod.getRandomMenu();
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Menu' failed to provide an identifier", id);
        obj = menuService.find(id);
        boolean modified =  dod.modifyMenu(obj);
        Integer currentVersion = obj.getVersion();
        Menu merged = menuService.update(obj);
        menuDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Menu' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveMenu() {
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", dod.getRandomMenu());
        Menu obj = dod.getNewTransientMenu(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Menu' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Menu' identifier to be null", obj.getId());
        menuService.save(obj);
        menuDao.flush();
        Assert.assertNotNull("Expected 'Menu' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteMenu() {
        Menu obj = dod.getRandomMenu();
        Assert.assertNotNull("Data on demand for 'Menu' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Menu' failed to provide an identifier", id);
        obj = menuService.find(id);
        menuService.delete(obj);
        menuDao.flush();
        Assert.assertNull("Failed to remove 'Menu' with identifier '" + id + "'", menuService.find(id));
    }
}
