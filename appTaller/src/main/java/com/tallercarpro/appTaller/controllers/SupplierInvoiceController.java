package com.tallercarpro.appTaller.controllers;

import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.models.domain.SupplierInvoice;
import com.tallercarpro.appTaller.service.SupplierInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/supplier-invoices")
public class SupplierInvoiceController {
    @Autowired
    private SupplierInvoiceService supplierInvoiceService;

    @PostMapping
    public ResponseEntity<SupplierInvoice> createSupplierInvoice(@RequestBody SupplierInvoice supplierInvoice) {
        SupplierInvoice createdSupplierInvoice = supplierInvoiceService.createSupplierInvoice(supplierInvoice);
        return new ResponseEntity<>(createdSupplierInvoice, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SupplierInvoice>> getAllSupplierInvoices() {
        List<SupplierInvoice> supplierInvoices = supplierInvoiceService.getAllSupplierInvoices();
        return new ResponseEntity<>(supplierInvoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierInvoice> getSupplierInvoiceById(@PathVariable String id) {
        SupplierInvoice supplierInvoice = supplierInvoiceService.getSupplierInvoiceById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Factura de proveedor no encontrada con ID: " + id));
        return new ResponseEntity<>(supplierInvoice, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierInvoice> updateSupplierInvoice(@PathVariable String id, @RequestBody SupplierInvoice supplierInvoice) {
        SupplierInvoice updatedSupplierInvoice = supplierInvoiceService.updateSupplierInvoice(id, supplierInvoice);
        return new ResponseEntity<>(updatedSupplierInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierInvoice(@PathVariable String id) {
        supplierInvoiceService.deleteSupplierInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
