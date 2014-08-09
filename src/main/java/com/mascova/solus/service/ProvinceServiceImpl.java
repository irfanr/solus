package com.mascova.solus.service;

import com.mascova.solus.dao.ProvinceDao;
import com.mascova.solus.entity.Province;
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
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceDao provinceDao;

    @Override
    public long countAll() {
        return provinceDao.count();
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        return Province.countEntries(filters);
    }

    @Override
    public void delete(Province province) {
        province = find(province.getId());
        provinceDao.delete(province);
    }

    @Override
    public Province find(Long id) {
        return provinceDao.findOne(id);
    }

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

    @Override
    public Province findByName(String name) {
        return Province.findByName(name);
    }

    @Override
    public List<Province> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return Province.findEntries(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public List<Province> findEntries(int firstResult, int maxResults) {
        return provinceDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Province> findEntries(int action, int startIndex, int blockSize,
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

            Query q = Province.entityManager().createQuery(baseSQL);
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
    public Province save(Province province) {
        return provinceDao.save(province);
    }

    @Override
    public Province update(Province province) {
        return provinceDao.save(province);
    }

    @Override
    public List<Province> save(List<Province> provinceList) {
        for (Province province : provinceList) {
            save(province);
        }

        return provinceList;
    }

    @Override
    public List<Province> update(List<Province> provinceList) {
        for (Province province : provinceList) {
            update(province);
        }

        return provinceList;
    }

    @Override
    public void delete(List<Province> provinceList) {
        for (Province province : provinceList) {
            delete(province);
        }
    }
}
