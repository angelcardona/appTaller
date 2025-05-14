package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.repository.SparePartRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.SparePart;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SparePartService {
    @Autowired
    private SparePartRepository sparePartRepository;

    @Transactional
    public SparePart createSparePart(SparePart sparePart) {
        // Validaciones
        if (sparePart.getName() == null || sparePart.getName().trim().isEmpty()) {
            throw new ValidationException("El nombre del repuesto es obligatorio");
        }
        if (sparePart.getCost() < 0) {
            throw new ValidationException("El costo del repuesto no puede ser negativo");
        }
        if (sparePart.getProfitPercentage() < 0) {
            throw new ValidationException("El porcentaje de ganancia no puede ser negativo");
        }

        return (SparePart) sparePartRepository.save(sparePart);
    }

    public List<SparePart> getAllSpareParts() {
        return sparePartRepository.findAll();
    }

    public Optional<SparePart> getSparePartById(String id) {
        return sparePartRepository.findById(id);
    }

    @Transactional
    public SparePart updateSparePart(String id, SparePart sparePart) {
        if (!sparePartRepository.existsById(id)) {
            throw new ResourceNotfoundException("Repuesto no encontrado con ID: " + id);
        }
        if (sparePart.getName() == null || sparePart.getName().trim().isEmpty()) {
            throw new ValidationException("El nombre del repuesto es obligatorio");
        }
        if (sparePart.getCost() < 0) {
            throw new ValidationException("El costo del repuesto no puede ser negativo");
        }
        if (sparePart.getProfitPercentage() < 0) {
            throw new ValidationException("El porcentaje de ganancia no puede ser negativo");
        }
        sparePart.setId(id);
        return (SparePart) sparePartRepository.save(sparePart);
    }

    @Transactional
    public void deleteSparePart(String id) {
        if (!sparePartRepository.existsById(id)) {
            throw new ResourceNotfoundException("Repuesto no encontrado con ID: " + id);
        }
        sparePartRepository.deleteById(id);
    }
}
