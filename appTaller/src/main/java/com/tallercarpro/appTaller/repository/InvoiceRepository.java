package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    List<Invoice> findByClientId(String clientId);

    List<Invoice> findByVehicleId(String vehicleId);

    List<Invoice> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

}
