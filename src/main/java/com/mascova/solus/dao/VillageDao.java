package com.mascova.solus.dao;

import com.mascova.solus.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageDao extends JpaSpecificationExecutor<Village>, JpaRepository<Village, Long> {
}
