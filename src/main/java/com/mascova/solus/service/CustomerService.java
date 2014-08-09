package com.mascova.solus.service;

import com.mascova.solus.entity.Customer;

public interface CustomerService extends DeskCrudService<Customer> {
    
    public abstract Customer findByName(String name);    
}
