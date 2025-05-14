package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,String> {
}
