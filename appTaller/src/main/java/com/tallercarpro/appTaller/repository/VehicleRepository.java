package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Client;
import com.tallercarpro.appTaller.models.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
    boolean existsByLicensePlate(String licensePlate);
    Vehicle findByLicensePlate(String licensePlate);

    List<Vehicle> findByClientId(String clientId);
}
