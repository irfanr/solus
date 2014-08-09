package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.entity.Person;
import com.mascova.qeela.entity.Product;
import com.mascova.qeela.dao.ProductDao;
import com.mascova.qeela.service.ProductService;
import java.math.BigDecimal;
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
public class ProductDataOnDemand {

    private Random rnd = new SecureRandom();
    private List<Product> data;
    @Autowired
    ProductService productService;
    @Autowired
    ProductDao productDao;

    public Product getNewTransientProduct(int index) {
        Product obj = new Product();
        setName(obj, index);
        setAmount(obj, index);
        setPrice(obj, index);
        return obj;
    }

    public void setName(Product obj, int index) {
        String name = "name_" + index;
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        obj.setName(name);
    }

    private void setAmount(Product obj, int index) {
        obj.setAmount(rnd.nextInt());
    }

    private void setPrice(Product obj, int index) {
        obj.setPrice( new BigDecimal(rnd.nextDouble()) );
    }

    public Product getSpecificProduct(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Product obj = data.get(index);
        Long id = obj.getId();
        return productService.find(id);
    }

    public Product getRandomProduct() {
        init();
        Product obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return productService.find(id);
    }

    public boolean modifyProduct(Product obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = productService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Product' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Product>();
        for (int i = 0; i < 10; i++) {
            Product obj = getNewTransientProduct(i);
            try {
                productService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            productDao.flush();
            data.add(obj);
        }
    }
}
