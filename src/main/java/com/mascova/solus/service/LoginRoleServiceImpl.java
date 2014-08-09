package com.mascova.solus.service;

import com.mascova.solus.dao.LoginRoleDao;
import com.mascova.solus.dao.LoginRoleDaoJdbc;
import com.mascova.solus.entity.LoginRole;
import com.mascova.solus.entity.Menu;
import com.mascova.solus.entity.Permission;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginRoleServiceImpl implements LoginRoleService {

    @Autowired
    LoginRoleDao loginRoleDao;
    @Autowired
    LoginRoleDaoJdbc loginRoleDaoJdbc;
    @Autowired
    PermissionService permissionService;
    @Autowired
    MenuService menuService;

    @Override
    public long countAll() {
        return loginRoleDao.count();
    }

    @Override
    public void delete(LoginRole loginRole) {
        loginRoleDao.delete(loginRole);
    }

    @Override
    public LoginRole findByCode(String code) {

        return LoginRole.findByCode(code);
    }

    @Override
    public LoginRole find(Long id) {
        return loginRoleDao.findOne(id);
    }

    @Override
    public List<LoginRole> findAll() {
        return loginRoleDao.findAll();
    }
    
    @Override
    public List<LoginRole> findEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public List<LoginRole> findEntries(int firstResult, int maxResults) {
        return loginRoleDao.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public LoginRole findLoginRoleWithPermissions(Long id) {
        return LoginRole.findLoginRoleWithPermissions(id);
    }

    @Override
    public LoginRole save(LoginRole loginRole) {
        return loginRoleDao.save(loginRole);
    }

    @Override
    public LoginRole update(LoginRole loginRole) {
        return loginRoleDao.save(loginRole);
    }

    @Override
    public void addPermissionFromLoginRole(LoginRole selectedLoginRole, String permissionCode) {

        Permission permission = permissionService.findByCode(permissionCode);
        selectedLoginRole = findLoginRoleWithPermissions(selectedLoginRole.getId());
        selectedLoginRole.getPermissions().add(permission);
        loginRoleDao.save(selectedLoginRole);
    }

    @Override
    public void removePermissionFromLoginRole(LoginRole selectedLoginRole, String permissionCode) {
        Permission permission = permissionService.findByCode(permissionCode);
        selectedLoginRole = findLoginRoleWithPermissions(selectedLoginRole.getId());
        selectedLoginRole.getPermissions().remove(permission);
        loginRoleDao.save(selectedLoginRole);
    }

    @Override
    public void addMenuToLoginRole(LoginRole selectedLoginRole, Menu menu) {

        selectedLoginRole = find(selectedLoginRole.getId());
        menu = menuService.find(menu.getId());

        if (menu.getParentMenu() != null) {
            addMenuToLoginRole(selectedLoginRole, menu.getParentMenu());
        }

        if (!isLoginRoleHasMenuAccess(selectedLoginRole, menu)) {
            loginRoleDaoJdbc.addMenuToLoginRole(selectedLoginRole.getId(), menu.getId());
        }

    }

    @Override
    public void removeMenuFromLoginRole(LoginRole selectedLoginRole, Menu menu) {

        selectedLoginRole = find(selectedLoginRole.getId());
        menu = menuService.find(menu.getId());

        if (menu.getParentMenu() != null) {
            if (!isOneOfMenuSiblingHasMenuAccess(selectedLoginRole, menu)) {
                removeMenuFromLoginRole(selectedLoginRole, menu.getParentMenu());
            }

        }

        if (isLoginRoleHasMenuAccess(selectedLoginRole, menu)) {
            loginRoleDaoJdbc.removeMenuFromLoginRole(selectedLoginRole.getId(), menu.getId());
        }
    }

    @Override
    public boolean isLoginRoleHasMenuAccess(LoginRole loginRole, Menu menu) {
        return loginRoleDaoJdbc.isLoginRoleHasMenuAccess(loginRole.getId(), menu.getId());
    }

    private boolean isOneOfMenuSiblingHasMenuAccess(LoginRole loginRole, Menu menu) {

        List<Menu> siblingMenus = menuService.getSiblingMenus(menu);

        for (Menu siblingMenu : siblingMenus) {

            if (isLoginRoleHasMenuAccess(loginRole, menu)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<LoginRole> save(List<LoginRole> loginRoleList) {
        for (LoginRole loginRole : loginRoleList) {
            save(loginRole);
        }
        
        return loginRoleList;
    }

    @Override
    public List<LoginRole> update(List<LoginRole> loginRoleList) {
        for (LoginRole loginRole : loginRoleList) {
            update(loginRole);
        }
        
        return loginRoleList;
    }

    @Override
    public void delete(List<LoginRole> loginRoleList) {
        for (LoginRole loginRole : loginRoleList) {
            delete(loginRole);
        }
    }

    @Override
    public long countEntries(Map<String, String> filters) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
