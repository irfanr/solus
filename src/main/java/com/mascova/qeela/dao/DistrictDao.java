package com.mascova.qeela.dao;

import com.mascova.qeela.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictDao extends JpaSpecificationExecutor<District>, JpaRepository<District, Long> {
}
