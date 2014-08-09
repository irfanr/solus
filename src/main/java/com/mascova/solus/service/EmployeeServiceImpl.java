/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.dao.EmployeeDao;
import com.mascova.solus.dao.VillageDao;
import com.mascova.solus.entity.Employee;
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
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    VillageDao villageDao;      

    @Override
    public long countAll() {
        return employeeDao.count();
    }

    @Override
    public void delete(Employee employee) {
        employeeDao.delete(employee);
    }

    @Override
    public Employee find(Long id) {
        return employeeDao.findOne(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
    
    @Override
    public Employee findByName(String name) {
        return Employee.findByName(name);
    }    

    @Override
    public List<Employee> findEntries(int firstResult, int maxResults) {
        return employeeDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Employee> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Employee> findEntries(int action, int startIndex, int blockSize, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, String baseSQL, Object[] paramValues) {
        
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

            Query q = Employee.entityManager().createQuery(baseSQL);
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
    public Employee save(Employee employee) {
        if( employee.getVillage() != null ){
            employee.setVillage(villageDao.findOne(employee.getVillage().getId()));
        }        
        return employeeDao.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public List<Employee> save(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            save(employee);
        }
        
        return employeeList;
    }

    @Override
    public List<Employee> update(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            update(employee);
        }
        
        return employeeList;
    }

    @Override
    public void delete(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            delete(employee);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
