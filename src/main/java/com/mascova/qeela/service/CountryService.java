package com.mascova.qeela.service;

import com.mascova.qeela.entity.Country;

public interface CountryService extends DeskCrudService<Country> {

    public abstract Country findByName(String name);   
}
