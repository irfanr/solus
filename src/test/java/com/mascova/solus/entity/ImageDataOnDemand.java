package com.mascova.solus.entity;

import com.mascova.solus.entity.Image;
import com.mascova.solus.dao.ImageDao;
import com.mascova.solus.service.ImageService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class ImageDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Image> data;

	@Autowired
    ImageService imageService;

	@Autowired
    ImageDao imageDao;

	public Image getNewTransientImage(int index) {
        Image obj = new Image();
        setCreated(obj, index);
        setFile(obj, index);
        setImagePath(obj, index);
        setModified(obj, index);
        return obj;
    }

	public void setCreated(Image obj, int index) {
        Date created = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreated(created);
    }

	public void setFile(Image obj, int index) {
        byte[] file = String.valueOf(index).getBytes();
        obj.setFile(file);
    }

	public void setImagePath(Image obj, int index) {
        String imagePath = "imagePath_" + index;
        if (imagePath.length() > 256) {
            imagePath = imagePath.substring(0, 256);
        }
        obj.setImagePath(imagePath);
    }

	public void setModified(Image obj, int index) {
        Date modified = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setModified(modified);
    }

	public Image getSpecificImage(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Image obj = data.get(index);
        Long id = obj.getId();
        return imageService.find(id);
    }

	public Image getRandomImage() {
        init();
        Image obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return imageService.find(id);
    }

	public boolean modifyImage(Image obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = imageService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Image' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Image>();
        for (int i = 0; i < 10; i++) {
            Image obj = getNewTransientImage(i);
            try {
                imageService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            imageDao.flush();
            data.add(obj);
        }
    }
}
