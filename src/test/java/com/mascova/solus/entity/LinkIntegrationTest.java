package com.mascova.solus.entity;

import com.mascova.solus.entity.Link;
import com.mascova.solus.dao.LinkDao;
import com.mascova.solus.service.LinkService;
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
public class LinkIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    LinkDataOnDemand dod;

	@Autowired
    LinkService linkService;

	@Autowired
    LinkDao linkDao;

	@Test
    public void testCountAllLinks() {
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", dod.getRandomLink());
        long count = linkService.countAll();
        Assert.assertTrue("Counter for 'Link' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindLink() {
        Link obj = dod.getRandomLink();
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Link' failed to provide an identifier", id);
        obj = linkService.find(id);
        Assert.assertNotNull("Find method for 'Link' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Link' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllLinks() {
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", dod.getRandomLink());
        long count = linkService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Link', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Link> result = linkService.findAll();
        Assert.assertNotNull("Find all method for 'Link' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Link' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindLinkEntries() {
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", dod.getRandomLink());
        long count = linkService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Link> result = linkService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Link' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Link' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Link obj = dod.getRandomLink();
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Link' failed to provide an identifier", id);
        obj = linkService.find(id);
        Assert.assertNotNull("Find method for 'Link' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyLink(obj);
        Integer currentVersion = obj.getVersion();
        linkDao.flush();
        Assert.assertTrue("Version for 'Link' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateLinkUpdate() {
        Link obj = dod.getRandomLink();
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Link' failed to provide an identifier", id);
        obj = linkService.find(id);
        boolean modified =  dod.modifyLink(obj);
        Integer currentVersion = obj.getVersion();
        Link merged = linkService.update(obj);
        linkDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Link' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveLink() {
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", dod.getRandomLink());
        Link obj = dod.getNewTransientLink(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Link' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Link' identifier to be null", obj.getId());
        linkService.save(obj);
        linkDao.flush();
        Assert.assertNotNull("Expected 'Link' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteLink() {
        Link obj = dod.getRandomLink();
        Assert.assertNotNull("Data on demand for 'Link' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Link' failed to provide an identifier", id);
        obj = linkService.find(id);
        linkService.delete(obj);
        linkDao.flush();
        Assert.assertNull("Failed to remove 'Link' with identifier '" + id + "'", linkService.find(id));
    }
}
