package com.tallercarpro.appTaller.Controllers;

import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.models.domain.Mechanic;
import com.tallercarpro.appTaller.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {
    @Autowired
    private MechanicService mechanicService;

    @PostMapping
    public ResponseEntity<Mechanic> createMechanic(@RequestBody Mechanic mechanic) {
        Mechanic createdMechanic = mechanicService.createMechanic(mechanic);
        return new ResponseEntity<>(createdMechanic, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mechanic>> getAllMechanics() {
        List<Mechanic> mechanics = mechanicService.getAllMechanics();
        return new ResponseEntity<>(mechanics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mechanic> getMechanicById(@PathVariable String id) {
        Mechanic mechanic = mechanicService.getMechanicById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Mecánico no encontrado con ID: " + id));
        return new ResponseEntity<>(mechanic, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mechanic> updateMechanic(@PathVariable String id, @RequestBody Mechanic mechanic) {
        Mechanic updatedMechanic = mechanicService.updateMechanic(Long.valueOf(id), mechanic);
        return new ResponseEntity<>(updatedMechanic, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMechanic(@PathVariable String id) {
        mechanicService.deleteMechanic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
