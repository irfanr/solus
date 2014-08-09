package com.mascova.solus.dao;

import com.mascova.solus.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<Login, Long>, JpaSpecificationExecutor<Login> {
}
