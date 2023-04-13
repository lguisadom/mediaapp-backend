package com.lagm.service;

import com.lagm.model.Patient;

import java.util.List;

public interface IPatientService {
    Patient save(Patient patient);
    Patient update(Patient patient, Integer id);
    List<Patient> findAll();
    Patient findById(Integer id);
    void delete(Integer id);
}
