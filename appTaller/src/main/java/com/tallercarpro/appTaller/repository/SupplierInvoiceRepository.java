package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Client;
import com.tallercarpro.appTaller.models.domain.SupplierInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierInvoiceRepository extends JpaRepository <SupplierInvoice,String>{
}
