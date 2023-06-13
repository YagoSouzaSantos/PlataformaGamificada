package com.ifba.plataformaGamificada.repository;

import com.ifba.plataformaGamificada.model.Activity;
import com.ifba.plataformaGamificada.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IQuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByPhase(Long phaseId);
}
