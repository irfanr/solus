package com.mascova.qeela.service;

import com.mascova.qeela.entity.Province;
import java.util.Map;

public interface ProvinceService extends DeskCrudService<Province> {

    public abstract long countEntries(Map<String, String> filters);

    public abstract Province findByName(String name);
}
