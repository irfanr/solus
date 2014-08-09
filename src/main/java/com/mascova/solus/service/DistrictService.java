package com.mascova.solus.service;

import com.mascova.solus.entity.District;
import java.util.Map;

public interface DistrictService extends DeskCrudService<District> {

    public abstract long countEntries(Map<String, String> filters);

    public abstract District findByName(String name);
}
