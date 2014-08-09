/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.solus.service;

import com.mascova.solus.entity.SystemLookup;
import java.util.List;
import java.util.Map;

/**
 *
 * @author irfan
 */
public interface SystemLookupService extends CrudService<SystemLookup> {

    public abstract long countByType(String type);

    public abstract SystemLookup find(String type, String code);

    public abstract List<SystemLookup> findByType(String type);

    public abstract Map<String, SystemLookup> findAllSystemLookupByTypeAsMap(String type);

    public abstract Map<String, SystemLookup> findAllAsMap();
}
