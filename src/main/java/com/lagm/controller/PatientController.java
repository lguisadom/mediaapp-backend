package com.lagm.controller;

import com.lagm.model.Patient;
import com.lagm.service.impl.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientServiceImpl service;

    @GetMapping
    public List<Patient> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Patient save(@RequestBody Patient patient) {
        return service.save(patient);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable("id") Integer id, @RequestBody Patient patient) {
        patient.setIdPatient(id);
        return service.save(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
