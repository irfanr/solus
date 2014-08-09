/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.dao.ServiceDao;
import com.mascova.solus.entity.Service;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irfan
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceDao serviceDao;

    @Override
    public long countAll() {
        return serviceDao.count();
    }

    @Override
    public void delete(Service service) {
        serviceDao.delete(service);
    }

    @Override
    public Service find(Long id) {
        return serviceDao.findOne(id);
    }

    @Override
    public List<Service> findAll() {
        return serviceDao.findAll();
    }

    @Override
    public List<Service> findEntries(int firstResult, int maxResults) {
        return serviceDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Service> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> findEntries(int action, int startIndex, int blockSize, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, String baseSQL, Object[] paramValues) {

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

            Query q = Service.entityManager().createQuery(baseSQL);
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
    public Service save(Service service) {
        return serviceDao.save(service);
    }

    @Override
    public Service update(Service service) {
        return serviceDao.save(service);
    }

    @Override
    public List<Service> save(List<Service> serviceList) {
        for (Service service : serviceList) {
            save(service);
        }
        return serviceList;
    }

    @Override
    public List<Service> update(List<Service> serviceList) {
        for (Service service : serviceList) {
            update(service);
        }
        return serviceList;
    }

    @Override
    public void delete(List<Service> serviceList) {
        for (Service service : serviceList) {
            delete(service);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
