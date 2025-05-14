package com.tallercarpro.appTaller.Repository;

import com.tallercarpro.appTaller.models.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public interface InvoiceRepository extends JpaRepository {

    List<Invoice> findByClientId(String clientId);

    List<Invoice> findByVehicleId(String vehicleId);

    List<Invoice> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
