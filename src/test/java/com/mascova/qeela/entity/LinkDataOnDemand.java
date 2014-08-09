package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Link;
import com.mascova.qeela.dao.LinkDao;
import com.mascova.qeela.service.LinkService;
import java.security.SecureRandom;
import java.util.ArrayList;
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
public class LinkDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Link> data;

	@Autowired
    LinkService linkService;

	@Autowired
    LinkDao linkDao;

	public Link getNewTransientLink(int index) {
        Link obj = new Link();
        setLinkPath(obj, index);
        return obj;
    }

	public void setLinkPath(Link obj, int index) {
        String linkPath = "linkPath_" + index;
        if (linkPath.length() > 256) {
            linkPath = linkPath.substring(0, 256);
        }
        obj.setLinkPath(linkPath);
    }

	public Link getSpecificLink(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Link obj = data.get(index);
        Long id = obj.getId();
        return linkService.find(id);
    }

	public Link getRandomLink() {
        init();
        Link obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return linkService.find(id);
    }

	public boolean modifyLink(Link obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = linkService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Link' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Link>();
        for (int i = 0; i < 10; i++) {
            Link obj = getNewTransientLink(i);
            try {
                linkService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            linkDao.flush();
            data.add(obj);
        }
    }
}
