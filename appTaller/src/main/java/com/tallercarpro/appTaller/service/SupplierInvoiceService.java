package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.repository.SupplierInvoiceRepository;
import com.tallercarpro.appTaller.repository.SupplierRepository;
import com.tallercarpro.appTaller.repository.VehicleRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.SupplierInvoice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierInvoiceService {
    @Autowired
    private SupplierInvoiceRepository supplierInvoiceRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public SupplierInvoice createSupplierInvoice(SupplierInvoice supplierInvoice) {
        if (supplierInvoice.getSupplier() == null || supplierInvoice.getSupplier().getId() == null) {
            throw new ValidationException("La factura debe estar asociada a un proveedor");
        }
        if (supplierInvoice.getDate() == null) {
            throw new ValidationException("La fecha de la factura es obligatoria");
        }
        if (supplierInvoice.getTotal() < 0) {
            throw new ValidationException("El total de la factura no puede ser negativo");
        }
        if (!supplierRepository.existsById(supplierInvoice.getSupplier().getId())) {
            throw new ResourceNotfoundException("Proveedor no encontrado con ID: " + supplierInvoice.getSupplier().getId());
        }
        if (supplierInvoice.getVehicle() != null && supplierInvoice.getVehicle().getId() != null) {
            if (!vehicleRepository.existsById(supplierInvoice.getVehicle().getId())) {
                throw new ResourceNotfoundException("Vehículo no encontrado con ID: " + supplierInvoice.getVehicle().getId());
            }
        }

        return (SupplierInvoice) supplierInvoiceRepository.save(supplierInvoice);
    }

    public List<SupplierInvoice> getAllSupplierInvoices() {
        return supplierInvoiceRepository.findAll();
    }

    public Optional<SupplierInvoice> getSupplierInvoiceById(String id) {
        return supplierInvoiceRepository.findById(id);
    }

    @Transactional
    public SupplierInvoice updateSupplierInvoice(String id, SupplierInvoice supplierInvoice) {
        if (!supplierInvoiceRepository.existsById(id)) {
            throw new ResourceNotfoundException("Factura de proveedor no encontrada con ID: " + id);
        }
        supplierInvoice.setId(id);
        return createSupplierInvoice(supplierInvoice);
    }

    @Transactional
    public void deleteSupplierInvoice(String id) {
        if (!supplierInvoiceRepository.existsById(id)) {
            throw new ResourceNotfoundException("Factura de proveedor no encontrada con ID: " + id);
        }
        supplierInvoiceRepository.deleteById(id);
    }
}
