package com.mascova.solus.dao;

import com.mascova.solus.entity.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDistrictDao extends JpaRepository<SubDistrict, Long>, JpaSpecificationExecutor<SubDistrict> {
}
