package com.mascova.qeela.entity;

import com.mascova.qeela.entity.Village;
import com.mascova.qeela.entity.Sales;
import com.mascova.qeela.dao.SalesDao;
import com.mascova.qeela.service.SalesService;
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

@Configurable
@Component
public class SalesDataOnDemand {

    private Random rnd = new SecureRandom();
    private List<Sales> data;
    @Autowired
    VillageDataOnDemand villageDataOnDemand;
    @Autowired
    CustomerDataOnDemand customerDataOnDemand;
    @Autowired
    EmployeeDataOnDemand employeeDataOnDemand;
    @Autowired
    ProductDataOnDemand productDataOnDemand;
    @Autowired
    ServiceDataOnDemand serviceDataOnDemand;
    @Autowired
    SalesService salesService;
    @Autowired
    SalesDao salesDao;

    public Sales getNewTransientSales(int index) {
        Sales obj = new Sales();

        setCreated(obj, index);
        setModified(obj, index);
        setAmount(obj, index);
        setPurchaseDate(obj, index);
        setCustomer(obj, index);
        setSeller(obj, index);
        setProductList(obj, index);
        setServiceList(obj, index);
        return obj;
    }

    public void setCreated(Sales obj, int index) {
        Date created = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreated(created);
    }

    public void setModified(Sales obj, int index) {
        Date modified = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setModified(modified);
    }

    private void setAmount(Sales obj, int index) {
        obj.setAmount(rnd.nextInt());
    }

    private void setPurchaseDate(Sales obj, int index) {
        Date purchased = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setPurchaseDate(purchased);
    }

    private void setCustomer(Sales obj, int index) {
        Customer customer = customerDataOnDemand.getRandomCustomer();
        obj.setCustomer(customer);
    }

    private void setSeller(Sales obj, int index) {
        Employee seller = employeeDataOnDemand.getRandomEmployee();
        obj.setSeller(seller);
    }

    private void setProductList(Sales obj, int index) {
        Product product = productDataOnDemand.getRandomProduct();
        obj.getProductList().add(product);
    }

    private void setServiceList(Sales obj, int index) {
        Service service = serviceDataOnDemand.getRandomService();
        obj.getServiceList().add(service);        
    }

    public Sales getSpecificSales(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Sales obj = data.get(index);
        Long id = obj.getId();
        return salesService.find(id);
    }

    public Sales getRandomSales() {
        init();
        Sales obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return salesService.find(id);
    }

    public boolean modifySales(Sales obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = salesService.findEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Sales' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Sales>();
        for (int i = 0; i < 10; i++) {
            Sales obj = getNewTransientSales(i);
            try {
                salesService.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            salesDao.flush();
            data.add(obj);
        }
    }
}
