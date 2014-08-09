package com.mascova.solus.dao;

import com.mascova.solus.entity.LoginRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRoleDao extends JpaSpecificationExecutor<LoginRole>, JpaRepository<LoginRole, Long> {
}
