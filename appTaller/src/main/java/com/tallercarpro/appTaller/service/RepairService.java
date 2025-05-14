package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.repository.MechanicRepository;
import com.tallercarpro.appTaller.repository.RepairRepository;
import com.tallercarpro.appTaller.repository.SparePartRepository;
import com.tallercarpro.appTaller.repository.VehicleRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.Repair;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService {
    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private SparePartRepository sparePartRepository;

    @Transactional
    public Repair createRepair(Repair repair) {
        if (repair.getRepairType() == null || repair.getRepairType().trim().isEmpty()) {
            throw new ValidationException("El tipo de reparación es obligatorio");
        }
        if (repair.getVehicle() == null || repair.getVehicle().getId() == null) {
            throw new ValidationException("La reparación debe estar asociada a un vehículo");
        }
        if (repair.getMechanic() == null || repair.getMechanic().getId()== null) {
            throw new ValidationException("La reparación debe estar asociada a un mecánico");
        }
        if (repair.getLaborCost() < 0) {
            throw new ValidationException("El costo de mano de obra no puede ser negativo");
        }
        if (repair.getMechanicLaborPercentage() < 0 || repair.getMechanicLaborPercentage() > 100) {
            throw new ValidationException("El porcentaje de mano de obra del mecánico debe estar entre 0 y 100");
        }
        if (!vehicleRepository.existsById(repair.getVehicle().getId())) {
            throw new ResourceNotfoundException("Vehículo no encontrado con ID: " + repair.getVehicle().getId());
        }
        if (!mechanicRepository.existsById(repair.getMechanic().getId())) {
            throw new ResourceNotfoundException("Mecánico no encontrado con ID: " + repair.getMechanic().getId());
        }
        if (repair.getSpareParts() != null) {
            for (var sparePart : repair.getSpareParts()) {
                if (!sparePartRepository.existsById(sparePart.getId())) {
                    throw new ResourceNotfoundException("Repuesto no encontrado con ID: " + sparePart.getId());
                }
            }
        }

        return (Repair) repairRepository.save(repair);
    }

    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }

    public Optional<Repair> getRepairById(String id) {
        return repairRepository.findById(id);
    }

    @Transactional
    public Repair updateRepair(String id, Repair repair) {
        if (!repairRepository.existsById(id)) {
            throw new ResourceNotfoundException("Reparación no encontrada con ID: " + id);
        }
        repair.setId(id);
        return createRepair(repair);
    }

    @Transactional
    public void deleteRepair(String id) {
        if (!repairRepository.existsById(id)) {
            throw new ResourceNotfoundException("Reparación no encontrada con ID: " + id);
        }
        repairRepository.deleteById(id);
    }

    public List<Repair> findByVehicleId(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new ResourceNotfoundException("Vehículo no encontrado con ID: " + vehicleId);
        }
        return repairRepository.findByVehicleId(vehicleId);
    }

    public List<Repair> findByStatus(String status) {
        return repairRepository.findByStatus(status);
    }
}
