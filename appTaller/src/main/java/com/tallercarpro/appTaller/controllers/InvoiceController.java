package com.tallercarpro.appTaller.controllers;

import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.models.domain.Invoice;
import com.tallercarpro.appTaller.service.InvoiceSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceSevice invoiceSevice;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceRequest request) {
        Invoice createdInvoice = invoiceSevice.createInvoice(request.getInvoice(), request.getRepairIds());
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceSevice.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable String id) {
        Invoice invoice = invoiceSevice.getInvoiceById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Factura no encontrada con ID: " + id));
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String id, @RequestBody InvoiceRequest request) {
        Invoice updatedInvoice = invoiceSevice.updateInvoice(id, request.getInvoice(), request.getRepairIds());
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable String id) {
        invoiceSevice.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Invoice>> findByClientId(@PathVariable String clientId) {
        List<Invoice> invoices = invoiceSevice.findByClientId(clientId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Invoice>> findByVehicleId(@PathVariable String vehicleId) {
        List<Invoice> invoices = invoiceSevice.findByVehicleId(vehicleId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    // DTO para manejar invoice y repairIds
    public static class InvoiceRequest {
        private Invoice invoice;
        private List<String> repairIds;

        public Invoice getInvoice() {
            return invoice;
        }

        public void setInvoice(Invoice invoice) {
            this.invoice = invoice;
        }

        public List<String> getRepairIds() {
            return repairIds;
        }

        public void setRepairIds(List<String> repairIds) {
            this.repairIds = repairIds;
        }
    }
}
