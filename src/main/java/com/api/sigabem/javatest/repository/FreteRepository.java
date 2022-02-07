package com.api.sigabem.javatest.repository;

import com.api.sigabem.javatest.model.Frete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {
    
}
