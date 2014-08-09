package com.mascova.solus.dao;

import com.mascova.solus.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictDao extends JpaSpecificationExecutor<District>, JpaRepository<District, Long> {
}
