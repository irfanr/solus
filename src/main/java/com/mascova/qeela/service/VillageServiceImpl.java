package com.mascova.qeela.service;

import com.mascova.qeela.dao.VillageDao;
import com.mascova.qeela.entity.Village;
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
public class VillageServiceImpl implements VillageService {

    @Autowired
    VillageDao villageDao;

    @Override
    public long countAll() {
        return villageDao.count();
    }

    @Override
    public long countEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return Village.countEntries(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public void delete(Village village) {

        village = find(village.getId());
        villageDao.delete(village);
    }

    @Override
    public Village find(Long id) {
        return villageDao.findOne(id);
    }

    @Override
    public List<Village> findAll() {
        return villageDao.findAll();
    }

    @Override
    public Village findByName(String name) {
        return Village.findByName(name);
    }

    @Override
    public List<Village> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return Village.findEntries(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public List<Village> findEntries(int firstResult, int maxResults) {
        return villageDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    @Override
    public List<Village> findEntries(int action, int startIndex, int blockSize, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, String baseSQL, Object[] paramValues) {
        
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

            Query q = Village.entityManager().createQuery(baseSQL);
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
    public Village save(Village village) {
        return villageDao.save(village);
    }

    @Override
    public Village update(Village village) {
        return villageDao.save(village);
    }

    @Override
    public List<Village> save(List<Village> villageList) {
        for (Village village : villageList) {
            save(village);
        }

        return villageList;
    }

    @Override
    public List<Village> update(List<Village> villageList) {
        for (Village village : villageList) {
            update(village);
        }

        return villageList;
    }

    @Override
    public void delete(List<Village> villageList) {
        for (Village village : villageList) {
            delete(village);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
