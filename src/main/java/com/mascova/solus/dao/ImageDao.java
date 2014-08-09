package com.mascova.solus.dao;

import com.mascova.solus.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {
}
