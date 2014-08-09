package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
}