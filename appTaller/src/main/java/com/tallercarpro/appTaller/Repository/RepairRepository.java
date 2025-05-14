package com.tallercarpro.appTaller.Repository;

import com.tallercarpro.appTaller.models.domain.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository {
    List<Repair> findByVehicleId(String vehicleId);

    List<Repair> findByStatus(String status);
}
