package com.mascova.qeela.dao;

import com.mascova.qeela.entity.SystemLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLookupDao extends JpaSpecificationExecutor<SystemLookup>, JpaRepository<SystemLookup, Long> {
}
