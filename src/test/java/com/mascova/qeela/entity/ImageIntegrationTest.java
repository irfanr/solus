package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Image;
import com.mascova.qeela.dao.ImageDao;
import com.mascova.qeela.service.ImageService;
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
public class ImageIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    ImageDataOnDemand dod;

	@Autowired
    ImageService imageService;

	@Autowired
    ImageDao imageDao;

	@Test
    public void testCountAllImages() {
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", dod.getRandomImage());
        long count = imageService.countAll();
        Assert.assertTrue("Counter for 'Image' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindImage() {
        Image obj = dod.getRandomImage();
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Image' failed to provide an identifier", id);
        obj = imageService.find(id);
        Assert.assertNotNull("Find method for 'Image' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Image' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllImages() {
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", dod.getRandomImage());
        long count = imageService.countAll();
        Assert.assertTrue("Too expensive to perform a find all test for 'Image', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Image> result = imageService.findAll();
        Assert.assertNotNull("Find all method for 'Image' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Image' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindImageEntries() {
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", dod.getRandomImage());
        long count = imageService.countAll();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Image> result = imageService.findEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Image' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Image' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Image obj = dod.getRandomImage();
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Image' failed to provide an identifier", id);
        obj = imageService.find(id);
        Assert.assertNotNull("Find method for 'Image' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyImage(obj);
        Integer currentVersion = obj.getVersion();
        imageDao.flush();
        Assert.assertTrue("Version for 'Image' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdateImageUpdate() {
        Image obj = dod.getRandomImage();
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Image' failed to provide an identifier", id);
        obj = imageService.find(id);
        boolean modified =  dod.modifyImage(obj);
        Integer currentVersion = obj.getVersion();
        Image merged = imageService.update(obj);
        imageDao.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Image' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSaveImage() {
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", dod.getRandomImage());
        Image obj = dod.getNewTransientImage(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Image' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Image' identifier to be null", obj.getId());
        imageService.save(obj);
        imageDao.flush();
        Assert.assertNotNull("Expected 'Image' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeleteImage() {
        Image obj = dod.getRandomImage();
        Assert.assertNotNull("Data on demand for 'Image' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Image' failed to provide an identifier", id);
        obj = imageService.find(id);
        imageService.delete(obj);
        imageDao.flush();
        Assert.assertNull("Failed to remove 'Image' with identifier '" + id + "'", imageService.find(id));
    }
}
