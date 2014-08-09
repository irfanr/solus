package com.mascova.qeela.dao;

import com.mascova.qeela.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageDao extends JpaSpecificationExecutor<Village>, JpaRepository<Village, Long> {
}
