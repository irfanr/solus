package com.mascova.qeela.service;

import com.mascova.qeela.dao.SystemLookupDao;
import com.mascova.qeela.entity.SystemLookup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemLookupServiceImpl implements SystemLookupService {

    @Autowired
    SystemLookupDao systemLookupDao;

    @Override
    public long countAll() {
        return systemLookupDao.count();
    }

    @Override
    public long countByType(String type) {
        return SystemLookup.countByType(type);
    }

    @Override
    public void delete(SystemLookup systemLookup) {
        systemLookupDao.delete(systemLookup);
    }

    @Override
    public SystemLookup find(Long id) {
        return systemLookupDao.findOne(id);
    }

    @Override
    public SystemLookup find(String type, String code) {
        return SystemLookup.findSystemLookup(type, code);
    }

    @Override
    public List<SystemLookup> findByType(String type) {
        return SystemLookup.findAllSystemLookupByType(type);
    }

    @Override
    public Map<String, SystemLookup> findAllSystemLookupByTypeAsMap(String type) {

        List<SystemLookup> systemLookupList = SystemLookup.findAllSystemLookupByType(type);

        Map<String, SystemLookup> systemLookupMap = new HashMap<>();

        for (SystemLookup systemLookup : systemLookupList) {
            systemLookupMap.put(systemLookup.getCode(), systemLookup);
        }

        return systemLookupMap;
    }

    @Override
    public List<SystemLookup> findAll() {
        return systemLookupDao.findAll();
    }

    @Override
    public Map<String, SystemLookup> findAllAsMap() {

        List<SystemLookup> systemLookupList = SystemLookup.findAllSystemLookups();

        Map<String, SystemLookup> systemLookupMap = new HashMap<>();

        for (SystemLookup systemLookup : systemLookupList) {
            systemLookupMap.put(
                    systemLookup.getType() + "-" + systemLookup.getCode(),
                    systemLookup);
        }

        return systemLookupMap;
    }

    @Override
    public List<SystemLookup> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemLookup> findEntries(int firstResult, int maxResults) {
        return systemLookupDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public SystemLookup save(SystemLookup systemLookup) {
        return systemLookupDao.save(systemLookup);
    }

    @Override
    public SystemLookup update(SystemLookup systemLookup) {
        return systemLookupDao.save(systemLookup);
    }

    @Override
    public List<SystemLookup> save(List<SystemLookup> systemLookupList) {
        for (SystemLookup systemLookup : systemLookupList) {
            save(systemLookup);
        }

        return systemLookupList;
    }

    @Override
    public List<SystemLookup> update(List<SystemLookup> systemLookupList) {
        for (SystemLookup systemLookup : systemLookupList) {
            update(systemLookup);
        }

        return systemLookupList;
    }

    @Override
    public void delete(List<SystemLookup> systemLookupList) {
        for (SystemLookup systemLookup : systemLookupList) {
            delete(systemLookup);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
