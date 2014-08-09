package com.mascova.solus.service;

import com.mascova.solus.entity.Province;
import java.util.Map;

public interface ProvinceService extends DeskCrudService<Province> {

    public abstract long countEntries(Map<String, String> filters);

    public abstract Province findByName(String name);
}
