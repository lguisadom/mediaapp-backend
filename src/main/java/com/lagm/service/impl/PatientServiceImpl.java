package com.lagm.service.impl;

import com.lagm.model.Patient;
import com.lagm.repo.IPatientRepo;
import com.lagm.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {
    private final IPatientRepo repo;


    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public Patient update(Patient patient, Integer id) {
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        Optional<Patient> optionalPatient = repo.findById(id);
        return optionalPatient.isPresent() ? optionalPatient.get() : new Patient();
        // return optionalPatient.orElseGet(() -> new Patient());
        // return optionalPatient.orElseGet(Patient::new);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
