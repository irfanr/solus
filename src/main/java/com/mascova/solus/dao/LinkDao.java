package com.mascova.solus.dao;

import com.mascova.solus.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkDao extends JpaRepository<Link, Long>, JpaSpecificationExecutor<Link> {
}
