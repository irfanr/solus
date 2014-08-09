package com.mascova.solus.dao;

import com.mascova.solus.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaSpecificationExecutor<Menu>, JpaRepository<Menu, Long> {
}
