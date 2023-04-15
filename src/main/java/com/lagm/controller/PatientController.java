package com.lagm.controller;

import com.lagm.model.Patient;
import com.lagm.service.impl.PatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        return new ResponseEntity<>(service.save(patient), HttpStatus.CREATED);
    }*/
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Patient patient) {
        Patient createdPatient = service.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPatient.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable("id") Integer id, @RequestBody Patient patient) {
        patient.setIdPatient(id);
        Patient updatedPatient = service.update(patient, id);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
