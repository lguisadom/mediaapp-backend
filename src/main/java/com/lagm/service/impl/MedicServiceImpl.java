package com.lagm.service.impl;

import com.lagm.model.Medic;
import com.lagm.repo.IGenericRepo;
import com.lagm.repo.IMedicRepo;
import com.lagm.service.IMedicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicServiceImpl extends CRUDImpl<Medic, Integer> implements IMedicService {
    private final IMedicRepo repo;

    @Override
    protected IGenericRepo<Medic, Integer> getRepo() {
        return repo;
    }
}
