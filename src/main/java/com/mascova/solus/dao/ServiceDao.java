package com.mascova.solus.dao;

import com.mascova.solus.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<Service, Long>, JpaSpecificationExecutor<Service> {
}
