package com.mascova.solus.dao;

import com.mascova.solus.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
}
