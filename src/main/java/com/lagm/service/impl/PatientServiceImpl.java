package com.lagm.service.impl;

import com.lagm.model.Patient;
import com.lagm.repo.IGenericRepo;
import com.lagm.repo.IPatientRepo;
import com.lagm.service.IPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl extends CRUDImpl<Patient, Integer> implements IPatientService {
    private final IPatientRepo repo;

    @Override
    protected IGenericRepo<Patient, Integer> getRepo() {
        return repo;
    }
}
