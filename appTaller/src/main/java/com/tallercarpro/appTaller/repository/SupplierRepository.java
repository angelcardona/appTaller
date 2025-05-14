package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Client;
import com.tallercarpro.appTaller.models.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository <Supplier,String>{
}
