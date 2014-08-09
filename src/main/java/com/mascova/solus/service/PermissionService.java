/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.entity.Login;
import com.mascova.solus.entity.Permission;
import java.util.List;

/**
 *
 * @author irfan
 */
public interface PermissionService extends CrudService<Permission> {

    public abstract Permission findByCode(String code);

    public abstract List<Permission> findByLogin(Login login);
}
