package com.lagm.service.impl;

import com.lagm.model.Consult;
import com.lagm.repo.IConsultRepo;
import com.lagm.repo.IGenericRepo;
import com.lagm.service.IConsultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {
    private final IConsultRepo repo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return repo;
    }
}
