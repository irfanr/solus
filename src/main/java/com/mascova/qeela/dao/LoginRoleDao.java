package com.mascova.qeela.dao;

import com.mascova.qeela.entity.LoginRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRoleDao extends JpaSpecificationExecutor<LoginRole>, JpaRepository<LoginRole, Long> {
}
