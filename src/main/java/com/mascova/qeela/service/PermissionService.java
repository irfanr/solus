/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.service;

import com.mascova.qeela.entity.Login;
import com.mascova.qeela.entity.Permission;
import java.util.List;

/**
 *
 * @author irfan
 */
public interface PermissionService extends CrudService<Permission> {

    public abstract Permission findByCode(String code);

    public abstract List<Permission> findByLogin(Login login);
}
