package com.lagm.service;

import com.lagm.model.Consult;
import com.lagm.model.Exam;

import java.util.List;

public interface IConsultService extends ICRUD<Consult, Integer> {
    Consult saveTransactional(Consult consult, List<Exam> exams);
}
