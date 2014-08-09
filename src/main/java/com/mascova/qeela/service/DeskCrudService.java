/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mascova.qeela.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author irfan
 */
public interface DeskCrudService<T> extends CrudService<T> {
    
    public abstract List<T> findEntries(int action, int startIndex, int blockSize,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType,
            String baseSQL,
            Object[] paramValues);        
    
}
