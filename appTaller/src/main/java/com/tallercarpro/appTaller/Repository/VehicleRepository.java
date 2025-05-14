package com.tallercarpro.appTaller.Repository;

import com.tallercarpro.appTaller.models.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository {
    boolean existsByLicensePlate(String licensePlate);
    Vehicle findByLicensePlate(String licensePlate);

    List<Vehicle> findByClientId(String clientId);
}
