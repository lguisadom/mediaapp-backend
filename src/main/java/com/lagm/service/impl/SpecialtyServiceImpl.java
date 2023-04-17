package com.lagm.service.impl;

import com.lagm.model.Specialty;
import com.lagm.repo.ISpecialtyRepo;
import com.lagm.repo.IGenericRepo;
import com.lagm.service.ISpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl extends CRUDImpl<Specialty, Integer> implements ISpecialtyService {
    private final ISpecialtyRepo repo;

    @Override
    protected IGenericRepo<Specialty, Integer> getRepo() {
        return repo;
    }
}
