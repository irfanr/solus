package com.mascova.qeela.service;

import com.mascova.qeela.entity.Employee;

public interface EmployeeService extends DeskCrudService<Employee> {
    
    public abstract Employee findByName(String name);    
}
