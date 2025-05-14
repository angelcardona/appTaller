package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.Repository.SupplierRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.Supplier;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        if (supplier.getName() == null || supplier.getName().trim().isEmpty()) {
            throw new ValidationException("El nombre del proveedor es obligatorio");
        }
        if (supplier.getContact() == null || supplier.getContact().trim().isEmpty()) {
            throw new ValidationException("La información de contacto es obligatoria");
        }

        return (Supplier) supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(String id) {
        return supplierRepository.findById(id);
    }

    @Transactional
    public Supplier updateSupplier(String id, Supplier supplier) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotfoundException("Proveedor no encontrado con ID: " + id);
        }
        if (supplier.getName() == null || supplier.getName().trim().isEmpty()) {
            throw new ValidationException("El nombre del proveedor es obligatorio");
        }
        if (supplier.getContact() == null || supplier.getContact().trim().isEmpty()) {
            throw new ValidationException("La información de contacto es obligatoria");
        }
        supplier.setId(id);
        return (Supplier) supplierRepository.save(supplier);
    }

    @Transactional
    public void deleteSupplier(String id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotfoundException("Proveedor no encontrado con ID: " + id);
        }
        supplierRepository.deleteById(id);
    }

}
