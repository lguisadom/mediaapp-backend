package com.lagm.service.impl;

import com.lagm.model.Consult;
import com.lagm.model.Exam;
import com.lagm.repo.IConsultExamRepo;
import com.lagm.repo.IConsultRepo;
import com.lagm.repo.IGenericRepo;
import com.lagm.service.IConsultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {
    private final IConsultRepo consultRepo;
    private final IConsultExamRepo ceRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    @Transactional
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        consultRepo.save(consult);
        exams.forEach(exam -> ceRepo.saveExam(consult.getIdConsult(), exam.getIdExam()));
        return consult;
    }
}
