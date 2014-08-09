package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaSpecificationExecutor<Menu>, JpaRepository<Menu, Long> {
}
