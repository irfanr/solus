package com.mascova.solus.service;

import com.mascova.solus.entity.SubDistrict;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

public interface SubDistrictService extends DeskCrudService<SubDistrict>{

    public abstract long countAll();

    public abstract long countEntries(Map<String, String> filters);

    public abstract void delete(SubDistrict subDistrict);

    public abstract SubDistrict findByName(String name);

    public abstract SubDistrict find(Long id);

    public abstract List<SubDistrict> findAll();

    public abstract List<SubDistrict> findEntries(int first, int pageSize,
            final String sortField, final SortOrder sortOrder,
            Map<String, String> filters);

    public abstract List<SubDistrict> findEntries(int firstResult, int maxResults);

    public abstract SubDistrict save(SubDistrict subDistrict);

    public abstract SubDistrict update(SubDistrict subDistrict);
}
