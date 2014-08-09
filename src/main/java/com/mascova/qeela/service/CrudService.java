/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.service;

import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author irfan
 */
public interface CrudService<T> {

    public abstract long countAll();
    
    public abstract long countEntries(Map<String, String> filters);    

    public abstract void delete(T t);
    
    public abstract void delete(List<T> t);    

    public abstract T find(Long id);

    public abstract List<T> findAll();

    public abstract List<T> findEntries(int firstResult, int maxResults);

    public abstract List<T> findEntries(int first, int pageSize, final String sortField,
            final SortOrder sortOrder, Map<String, String> filters);

    public abstract T save(T t);
    
    public abstract List<T> save(List<T> t);    

    public abstract T update(T t);
    
    public abstract List<T> update(List<T> t);      
}
