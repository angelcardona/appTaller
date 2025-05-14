package com.tallercarpro.appTaller.controllers;

import com.tallercarpro.appTaller.exception.ResourceNotfoundException;
import com.tallercarpro.appTaller.models.domain.SparePart;
import com.tallercarpro.appTaller.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spare-parts")
public class SparePartController {
    @Autowired
    private SparePartService sparePartService;

    @PostMapping
    public ResponseEntity<SparePart> createSparePart(@RequestBody SparePart sparePart) {
        SparePart createdSparePart = sparePartService.createSparePart(sparePart);
        return new ResponseEntity<>(createdSparePart, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SparePart>> getAllSpareParts() {
        List<SparePart> spareParts = sparePartService.getAllSpareParts();
        return new ResponseEntity<>(spareParts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SparePart> getSparePartById(@PathVariable String id) {
        SparePart sparePart = sparePartService.getSparePartById(id)
                .orElseThrow(() -> new ResourceNotfoundException("Repuesto no encontrado con ID: " + id));
        return new ResponseEntity<>(sparePart, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SparePart> updateSparePart(@PathVariable String id, @RequestBody SparePart sparePart) {
        SparePart updatedSparePart = sparePartService.updateSparePart(id, sparePart);
        return new ResponseEntity<>(updatedSparePart, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSparePart(@PathVariable String id) {
        sparePartService.deleteSparePart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
