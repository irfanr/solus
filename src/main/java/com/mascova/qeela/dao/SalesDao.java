package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDao extends JpaRepository<Sales, Long>, JpaSpecificationExecutor<Sales> {
}
