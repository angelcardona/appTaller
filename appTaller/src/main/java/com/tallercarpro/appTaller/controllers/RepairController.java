package com.tallercarpro.appTaller.controllers;

import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.models.domain.Repair;
import com.tallercarpro.appTaller.service.RepairService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
public class RepairController {
    @Autowired
    private RepairService repairService;

    @PostMapping
    public ResponseEntity<Repair> createRepair(@RequestBody Repair repair) {
        Repair createdRepair = repairService.createRepair(repair);
        return new ResponseEntity<>(createdRepair, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Repair>> getAllRepairs() {
        List<Repair> repairs = repairService.getAllRepairs();
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repair> getRepairById(@PathVariable String id) {
        Repair repair = repairService.getRepairById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Reparación no encontrada con ID: " + id));
        return new ResponseEntity<>(repair, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repair> updateRepair(@PathVariable String id, @RequestBody Repair repair) {
        Repair updatedRepair = repairService.updateRepair(id, repair);
        return new ResponseEntity<>(updatedRepair, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable String id) {
        repairService.deleteRepair(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Repair>> findByVehicleId(@PathVariable String vehicleId) {
        List<Repair> repairs = repairService.findByVehicleId(vehicleId);
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Repair>> findByStatus(@PathVariable String status) {
        List<Repair> repairs = repairService.findByStatus(status);
        return new ResponseEntity<>(repairs, HttpStatus.OK);
    }
}
