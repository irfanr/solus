package com.mascova.qeela.service;

import com.mascova.qeela.dao.DistrictDao;
import com.mascova.qeela.entity.District;
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
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictDao districtDao;

    @Override
    public long countAll() {
        return districtDao.count();
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        return District.countEntries(filters);
    }

    @Override
    public void delete(District district) {

        district = find(district.getId());
        districtDao.delete(district);
    }

    @Override
    public District findByName(String name) {
        return District.findByName(name);
    }

    @Override
    public District find(Long id) {
        return districtDao.findOne(id);
    }

    @Override
    public List<District> findAll() {
        return districtDao.findAll();
    }

    @Override
    public List<District> findEntries(int firstResult, int maxResults) {
        return districtDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<District> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return District.findEntries(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public List<District> findEntries(int action, int startIndex, int blockSize,
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

            Query q = District.entityManager().createQuery(baseSQL);
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
    public District save(District district) {
        return districtDao.save(district);
    }

    @Override
    public District update(District district) {
        return districtDao.save(district);
    }

    @Override
    public List<District> save(List<District> districtList) {
        for (District district : districtList) {
            save(district);
        }

        return districtList;
    }

    @Override
    public List<District> update(List<District> districtList) {
        for (District district : districtList) {
            update(district);
        }

        return districtList;
    }

    @Override
    public void delete(List<District> districtList) {
        for (District district : districtList) {
            delete(district);
        }
    }
}
