package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaSpecificationExecutor<Country>, JpaRepository<Country, Long> {
}
