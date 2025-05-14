package com.tallercarpro.appTaller.Repository;

import com.tallercarpro.appTaller.models.domain.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechanicRepository extends JpaRepository {
    boolean existsByName(String name);
    Mechanic findByname(String name);
}
