package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.Repository.MechanicRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.Mechanic;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class MechanicService {

    @Autowired
    private MechanicRepository mechanicRepository;

    @Transactional
    public Mechanic createMechanic(Mechanic mechanic){
        if(mechanic.getName()==null || mechanic.getName().trim().isEmpty()){
            throw new ValidationException("El nombre del mecanico es obligatorio");

        }
        if(mechanic.getPhone()==null || mechanic.getPhone().trim().isEmpty()){
            throw new ValidationException("El telefono del mecanico es obligatorio");

        }
        if (mechanicRepository.existsByName(mechanic.getName())){
            throw new ValidationException("Ya existe un mecanico con ese nombre");

        }if (mechanic.getPhone() != null && !mechanic.getPhone().matches("^\\+?\\d{10,15}$")) {
            throw new ValidationException("El teléfono debe ser un número válido");
        }
        return (Mechanic) mechanicRepository.save(mechanic);
    }
    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    public Optional<Mechanic> getMechanicById(String id) {
        return mechanicRepository.findById(id);
    }

    @Transactional
    public Mechanic updateMechanic(Long id, Mechanic mechanic) {
        if (!mechanicRepository.existsById(id)) {
            throw new ResolutionException("Mecánico no encontrado con ID: " + id);
        }
        if (mechanic.getName() == null || mechanic.getName().trim().isEmpty()) {
            throw new ValidationException("El nombre del mecánico es obligatorio");
        }
        if (mechanic.getPhone() == null || mechanic.getPhone().trim().isEmpty()) {
            throw new ValidationException("El telefono del mecánico es obligatorio");
        }
        Optional<Mechanic> existingMechanic = Optional.ofNullable(mechanicRepository.findByname(mechanic.getName()));
        if (existingMechanic.isPresent() && !Boolean.parseBoolean(existingMechanic.get().getId())) {
            throw new ValidationException("Ya existe otro mecánico con ese email");
        }
        mechanic.setId(String.valueOf(id));
        return (Mechanic) mechanicRepository.save(mechanic);
    }
    @Transactional
    public void deleteMechanic(String id) {
        if (!mechanicRepository.existsById(id)) {
            throw new ResourceNotfoundException("Mecánico no encontrado con ID: " + id);
        }
        mechanicRepository.deleteById(id);
    }



}
