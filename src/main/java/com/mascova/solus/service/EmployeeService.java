package com.mascova.solus.service;

import com.mascova.solus.entity.Employee;

public interface EmployeeService extends DeskCrudService<Employee> {
    
    public abstract Employee findByName(String name);    
}
