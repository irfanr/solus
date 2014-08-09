package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceDao extends JpaRepository<Province, Long>, JpaSpecificationExecutor<Province> {
}
