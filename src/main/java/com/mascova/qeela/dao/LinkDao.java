package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkDao extends JpaRepository<Link, Long>, JpaSpecificationExecutor<Link> {
}
