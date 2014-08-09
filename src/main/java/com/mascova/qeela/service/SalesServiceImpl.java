/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.service;

import com.mascova.qeela.dao.SalesDao;
import com.mascova.qeela.entity.Sales;
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
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesDao salesDao;

    @Override
    public long countAll() {
        return salesDao.count();
    }

    @Override
    public void delete(Sales sales) {
        salesDao.delete(sales);
    }

    @Override
    public Sales find(Long id) {
        return salesDao.findOne(id);
    }

    @Override
    public List<Sales> findAll() {
        return salesDao.findAll();
    }

    @Override
    public List<Sales> findEntries(int firstResult, int maxResults) {
        return salesDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Sales> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sales> findEntries(int action, int startIndex, int blockSize, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, String baseSQL, Object[] paramValues) {

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

            Query q = Sales.entityManager().createQuery(baseSQL);
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
    public Sales save(Sales sales) {
        return salesDao.save(sales);
    }

    @Override
    public Sales update(Sales sales) {
        return salesDao.save(sales);
    }

    @Override
    public List<Sales> save(List<Sales> salesList) {
        for (Sales sales : salesList) {
            save(sales);
        }

        return salesList;
    }

    @Override
    public List<Sales> update(List<Sales> salesList) {
        for (Sales sales : salesList) {
            update(sales);
        }

        return salesList;
    }

    @Override
    public void delete(List<Sales> salesList) {
        for (Sales sales : salesList) {
            delete(sales);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
