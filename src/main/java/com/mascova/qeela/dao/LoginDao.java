package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<Login, Long>, JpaSpecificationExecutor<Login> {
}
