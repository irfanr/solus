package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Service, Long>, JpaSpecificationExecutor<Service> {
}
