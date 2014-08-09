package com.mascova.solus.dao;

import com.mascova.solus.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaSpecificationExecutor<Country>, JpaRepository<Country, Long> {
}
