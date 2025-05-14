package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Client;
import com.tallercarpro.appTaller.models.domain.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechanicRepository extends JpaRepository <Mechanic,String>{
    boolean existsByName(String name);
    Mechanic findByname(String name);
}
