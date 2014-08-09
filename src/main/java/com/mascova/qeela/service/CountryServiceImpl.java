package com.mascova.qeela.service;

import com.mascova.qeela.dao.CountryDao;
import com.mascova.qeela.entity.Country;
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

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryDao countryDao;

    @Override
    public long countAll() {
        return countryDao.count();
    }

    @Override
    public void delete(Country country) {
        countryDao.delete(country);
    }

    @Override
    public Country find(Long id) {
        return countryDao.findOne(id);
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Country findByName(String name) {
        return Country.findByName(name);
    }

    @Override
    public List<Country> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return Country.findEntries(first, pageSize, sortField, sortOrder, filters);
    }

    public List<Country> findEntries(int firstResult, int maxResults) {
        return countryDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Country> findEntries(int action, int startIndex, int blockSize,
            Map filteredColumns, ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns, Class valueObjectType, String baseSQL,
            Object[] paramValues) {

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

            Query q = Country.entityManager().createQuery(baseSQL);
            for (int i = 0; i < values.size(); i++) {
                q.setParameter(i + 1, values.get(i));
            }

            q.setFirstResult(startIndex);
            q.setMaxResults(blockSize);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ProvinceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Country save(Country country) {
        return countryDao.save(country);
    }

    @Override
    public Country update(Country country) {
        return countryDao.save(country);
    }

    @Override
    public List<Country> save(List<Country> countryList) {
        for (Country country : countryList) {
            save(country);
        }

        return countryList;
    }

    @Override
    public List<Country> update(List<Country> countryList) {
        for (Country country : countryList) {
            update(country);
        }

        return countryList;
    }

    @Override
    public void delete(List<Country> countryList) {
        for (Country country : countryList) {
            delete(country);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
