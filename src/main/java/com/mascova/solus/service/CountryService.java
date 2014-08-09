package com.mascova.solus.service;

import com.mascova.solus.entity.Country;

public interface CountryService extends DeskCrudService<Country> {

    public abstract Country findByName(String name);   
}
