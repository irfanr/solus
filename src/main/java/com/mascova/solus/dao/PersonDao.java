package com.mascova.solus.dao;

import com.mascova.solus.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
}
