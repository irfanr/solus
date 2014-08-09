package com.mascova.qeela.service;

import com.mascova.qeela.entity.Village;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author irfan
 */
public interface VillageService extends DeskCrudService<Village> {

    public abstract long countEntries(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters);

    public abstract Village findByName(String name);
}
