package com.mascova.solus.dao;

import com.mascova.solus.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
}
