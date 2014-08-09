/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.dao.PermissionDao;
import com.mascova.solus.entity.Login;
import com.mascova.solus.entity.Permission;
import java.util.List;
import java.util.Map;
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
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public long countAll() {
        return permissionDao.count();
    }

    @Override
    public void delete(Permission permission) {
        permissionDao.delete(permission);
    }

    @Override
    public Permission find(Long id) {
        return permissionDao.findOne(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findEntries(int firstResult, int maxResults) {
        return permissionDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public List<Permission> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        return Permission.findEntries(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public Permission findByCode(String code) {
        return Permission.findPermissionByCode(code);
    }

    @Override
    public List<Permission> findByLogin(Login login) {
        return Permission.findPermissionByLogin(login);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public List<Permission> save(List<Permission> permissionList) {
        for (Permission permission : permissionList) {
            save(permission);
        }
        
        return permissionList;
    }

    @Override
    public List<Permission> update(List<Permission> permissionList) {
        for (Permission permission : permissionList) {
            update(permission);
        }
        
        return permissionList;
    }

    @Override
    public void delete(List<Permission> permissionList) {
        for (Permission permission : permissionList) {
            delete(permission);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
