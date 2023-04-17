package com.lagm.service.impl;

import com.lagm.model.Exam;
import com.lagm.repo.IGenericRepo;
import com.lagm.repo.IExamRepo;
import com.lagm.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {
    private final IExamRepo repo;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }
}
