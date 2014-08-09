package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {
}
