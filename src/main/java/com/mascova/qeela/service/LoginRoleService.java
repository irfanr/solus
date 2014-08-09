package com.mascova.qeela.service;

import com.mascova.qeela.entity.LoginRole;
import com.mascova.qeela.entity.Menu;
import java.util.List;

public interface LoginRoleService extends CrudService<LoginRole>{

    public abstract LoginRole findByCode(String code);

    public abstract LoginRole findLoginRoleWithPermissions(Long id);

    public abstract void addPermissionFromLoginRole(LoginRole selectedLoginRole, String permissionCode);

    public abstract void removePermissionFromLoginRole(LoginRole selectedLoginRole, String permissionCode);

    public abstract void addMenuToLoginRole(LoginRole selectedLoginRole, Menu menu);

    public abstract void removeMenuFromLoginRole(LoginRole selectedLoginRole, Menu menu);

    public abstract boolean isLoginRoleHasMenuAccess(LoginRole loginRole, Menu menu);
}
