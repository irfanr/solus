package com.mascova.qeela.service;

import com.mascova.qeela.dao.SubDistrictDao;
import com.mascova.qeela.entity.SubDistrict;
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
public class SubDistrictServiceImpl implements SubDistrictService {

    @Autowired
    SubDistrictDao subDistrictDao;

    @Override
    public long countAll() {
        return subDistrictDao.count();
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        return SubDistrict.countEntries(filters);
    }

    @Override
    public void delete(SubDistrict subDistrict) {

        subDistrict = find(subDistrict.getId());
        subDistrictDao.delete(subDistrict);
    }

    @Override
    public SubDistrict findByName(String name) {
        return SubDistrict.findByName(name);
    }

    @Override
    public List<SubDistrict> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return SubDistrict.findEntries(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public SubDistrict find(Long id) {
        return subDistrictDao.findOne(id);
    }

    @Override
    public List<SubDistrict> findAll() {
        return subDistrictDao.findAll();
    }

    @Override
    public List<SubDistrict> findEntries(int firstResult, int maxResults) {
        return subDistrictDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<SubDistrict> findEntries(int action, int startIndex, int blockSize,
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

            Query q = SubDistrict.entityManager().createQuery(baseSQL);
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
    public SubDistrict save(SubDistrict subDistrict) {
        return subDistrictDao.save(subDistrict);
    }

    @Override
    public SubDistrict update(SubDistrict subDistrict) {
        return subDistrictDao.save(subDistrict);
    }

    @Override
    public List<SubDistrict> save(List<SubDistrict> subDistrictList) {
        for (SubDistrict subDistrict : subDistrictList) {
            save(subDistrict);
        }

        return subDistrictList;
    }

    @Override
    public List<SubDistrict> update(List<SubDistrict> subDistrictList) {
        for (SubDistrict subDistrict : subDistrictList) {
            update(subDistrict);
        }

        return subDistrictList;
    }

    @Override
    public void delete(List<SubDistrict> subDistrictList) {
        for (SubDistrict subDistrict : subDistrictList) {
            delete(subDistrict);
        }
    }
}
