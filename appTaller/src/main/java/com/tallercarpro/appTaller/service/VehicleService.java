package com.tallercarpro.appTaller.service;

import com.tallercarpro.appTaller.repository.ClientRepository;
import com.tallercarpro.appTaller.repository.VehicleRepository;
import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.exception.ValidationException;
import com.tallercarpro.appTaller.models.domain.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getBrand() == null || vehicle.getBrand().trim().isEmpty()) {
            throw new ValidationException("La marca del vehículo es obligatoria");
        }
        if (vehicle.getModel() == null || vehicle.getModel().trim().isEmpty()) {
            throw new ValidationException("El modelo del vehículo es obligatorio");
        }
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().trim().isEmpty()) {
            throw new ValidationException("La placa del vehículo es obligatoria");
        }
        if (vehicleRepository.existsByLicensePlate(vehicle.getLicensePlate())) {
            throw new ValidationException("Ya existe un vehículo con esa placa");
        }
        if (vehicle.getYear() < 1900 || vehicle.getYear() > java.time.Year.now().getValue() + 1) {
            throw new ValidationException("El año del vehículo no es válido");
        }
        if (vehicle.getClient() == null || vehicle.getClient().getId() == null) {
            throw new ValidationException("El vehículo debe estar asociado a un cliente");
        }
        if (!clientRepository.existsById(vehicle.getClient().getId())) {
            throw new ResourceNotfoundException("Cliente no encontrado con ID: " + vehicle.getClient().getId());
        }

        return (Vehicle) vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    @Transactional
    public Vehicle updateVehicle(String id, Vehicle vehicle) {
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotfoundException("Vehículo no encontrado con ID: " + id);
        }
        if (vehicle.getBrand() == null || vehicle.getBrand().trim().isEmpty()) {
            throw new ValidationException("La marca del vehículo es obligatoria");
        }
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().trim().isEmpty()) {
            throw new ValidationException("La placa del vehículo es obligatoria");
        }
        Optional<Vehicle> existingVehicle = Optional.ofNullable(vehicleRepository.findByLicensePlate(vehicle.getLicensePlate()));
        if (existingVehicle.isPresent() && !existingVehicle.get().getId().equals(id)) {
            throw new ValidationException("Ya existe otro vehículo con esa placa");
        }
        vehicle.setId(id);
        return (Vehicle) vehicleRepository.save(vehicle);
    }

    @Transactional
    public void deleteVehicle(String id) {
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotfoundException("Vehículo no encontrado con ID: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> findByClientId(String clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotfoundException("Cliente no encontrado con ID: " + clientId);
        }
        return vehicleRepository.findByClientId(clientId);
    }
}
