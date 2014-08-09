/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.dao;

/**
 *
 * @author irfan
 */
public interface LoginRoleDaoJdbc {

    public abstract void addMenuToLoginRole(Long loginRoleId, Long menuId);

    public abstract void removeMenuFromLoginRole(Long loginRoleId, Long menuId);

    public boolean isLoginRoleHasMenuAccess(Long loginRoleId, Long menuId);
}
