package com.mascova.qeela.dao;

import com.mascova.qeela.entity.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDistrictDao extends JpaRepository<SubDistrict, Long>, JpaSpecificationExecutor<SubDistrict> {
}
