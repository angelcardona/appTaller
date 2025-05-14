package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Client;
import com.tallercarpro.appTaller.models.domain.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository <Repair,String> {
    List<Repair> findByVehicleId(String vehicleId);

    List<Repair> findByStatus(String status);
}
