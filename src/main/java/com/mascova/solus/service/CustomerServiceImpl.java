/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.dao.CustomerDao;
import com.mascova.solus.dao.VillageDao;
import com.mascova.solus.entity.Customer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.openswing.swing.util.server.JPAUtils;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irfan
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    VillageDao villageDao;    

    @Override
    public long countAll() {
        return customerDao.count();
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public Customer find(Long id) {
        return customerDao.findOne(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
    
    @Override
    public Customer findByName(String name) {
        return Customer.findByName(name);
    }    

    @Override
    public List<Customer> findEntries(int firstResult, int maxResults) {
        return customerDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Customer> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Customer> findEntries(int action, int startIndex, int blockSize, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, String baseSQL, Object[] paramValues) {

        try {
            ArrayList values = new ArrayList();
            values.addAll(Arrays.asList(paramValues));
            baseSQL = JPAUtils.applyFiltersAndSorter(
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    baseSQL,
                    values,
                    JPAUtils.getValueObjectAlias(valueObjectType, baseSQL));

            Query q = Customer.entityManager().createQuery(baseSQL);
            for (int i = 0; i < values.size(); i++) {
                q.setParameter(i + 1, values.get(i));
            }

            q.setFirstResult(startIndex);
            q.setMaxResults(blockSize);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(LoginServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }    

    @Override
    public Customer save(Customer customer) {
        if( customer.getVillage() != null ){
            customer.setVillage(villageDao.findOne(customer.getVillage().getId()));
        }
        return customerDao.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public List<Customer> save(List<Customer> customerList) {
        for (Customer customer : customerList) {
            save(customer);
        }
        
        return customerList;
    }

    @Override
    public List<Customer> update(List<Customer> customerList) {
        for (Customer customer : customerList) {
            update(customer);
        }
        
        return customerList;
    }

    @Override
    public void delete(List<Customer> customerList) {
        for (Customer customer : customerList) {
            delete(customer);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
