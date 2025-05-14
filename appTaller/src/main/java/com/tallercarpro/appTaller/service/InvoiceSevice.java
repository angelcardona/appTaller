package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.repository.ClientRepository;
import com.tallercarpro.appTaller.repository.InvoiceRepository;
import com.tallercarpro.appTaller.repository.RepairRepository;
import com.tallercarpro.appTaller.repository.VehicleRepository;
import com.tallercarpro.appTaller.exception.BusinessException;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.Invoice;
import com.tallercarpro.appTaller.models.domain.Repair;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceSevice {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Transactional
    public Invoice createInvoice(Invoice invoice, List<String> repairIds) {
        if (invoice.getClient() == null || invoice.getClient().getId() == null) {
            throw new ValidationException("La factura debe estar asociada a un cliente");
        }
        if (invoice.getVehicle() == null || invoice.getVehicle().getId() == null) {
            throw new ValidationException("La factura debe estar asociada a un vehículo");
        }
        if (invoice.getDate() == null) {
            throw new ValidationException("La fecha de la factura es obligatoria");
        }
        if (repairIds == null || repairIds.isEmpty()) {
            throw new BusinessException("No se puede crear una factura sin reparaciones asociadas");
        }
        if (!clientRepository.existsById(invoice.getClient().getId())) {
            throw new ResourceNotfoundException("Cliente no encontrado con ID: " + invoice.getClient().getId());
        }
        if (!vehicleRepository.existsById(invoice.getVehicle().getId())) {
            throw new ResourceNotfoundException("Vehículo no encontrado con ID: " + invoice.getVehicle().getId());
        }

        List<Repair> repairs = repairRepository.findAllById(repairIds);
        if (repairs.size() != repairIds.size()) {
            throw new ResourceNotfoundException("Una o más reparaciones no encontradas");
        }
        String vehicleId = invoice.getVehicle().getId();
        for (Repair repair : repairs) {
            if (!repair.getVehicle().getId().equals(vehicleId)) {
                throw new ValidationException("Todas las reparaciones deben pertenecer al mismo vehículo");
            }
        }

        double laborCost = repairs.stream()
                .mapToDouble(Repair::getLaborCost)
                .sum();
        double sparePartsCost = repairs.stream()
                .flatMap(repair -> repair.getSpareParts().stream())
                .mapToDouble(sp -> sp.getCost() * (1 + sp.getProfitPercentage() / 100))
                .sum();
        Double otherCostValue = invoice.getOtherCost();
        double otherCosts = (otherCostValue != null) ? otherCostValue : 0.0;

        invoice.setLaborCost(laborCost);
        invoice.setSparePartCost(sparePartsCost);
        invoice.setOtherCost(otherCosts);
        invoice.setTotal(laborCost + sparePartsCost + otherCosts);

        return (Invoice) invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(String id) {
        return invoiceRepository.findById(id);
    }

    @Transactional
    public Invoice updateInvoice(String id, Invoice invoice, List<String> repairIds) {
        if (!invoiceRepository.existsById(id)) {
            throw new ResourceNotfoundException("Factura no encontrada con ID: " + id);
        }
        invoice.setId(id);
        return createInvoice(invoice, repairIds);
    }

    @Transactional
    public void deleteInvoice(String id) {
        if (!invoiceRepository.existsById(id)) {
            throw new ResourceNotfoundException("Factura no encontrada con ID: " + id);
        }
        invoiceRepository.deleteById(id);
    }

    public List<Invoice> findByClientId(String clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotfoundException("Cliente no encontrado con ID: " + clientId);
        }
        return invoiceRepository.findByClientId(clientId);
    }

    public List<Invoice> findByVehicleId(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new ResourceNotfoundException("Vehículo no encontrado con ID: " + vehicleId);
        }
        return invoiceRepository.findByVehicleId(vehicleId);
    }
}
