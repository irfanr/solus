package com.mascova.qeela.service;

import com.mascova.qeela.entity.Customer;

public interface CustomerService extends DeskCrudService<Customer> {
    
    public abstract Customer findByName(String name);    
}
