package com.mascova.qeela.service;

import com.mascova.qeela.entity.District;
import java.util.Map;

public interface DistrictService extends DeskCrudService<District> {

    public abstract long countEntries(Map<String, String> filters);

    public abstract District findByName(String name);
}
