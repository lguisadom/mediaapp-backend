package com.lagm.repo;

import com.lagm.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends JpaRepository<Patient, Integer> {

}
