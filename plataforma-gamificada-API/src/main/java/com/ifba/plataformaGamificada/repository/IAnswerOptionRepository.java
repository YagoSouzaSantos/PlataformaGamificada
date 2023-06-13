package com.ifba.plataformaGamificada.repository;

import com.ifba.plataformaGamificada.model.AnswerOption;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAnswerOptionRepository extends JpaRepository<AnswerOption, Long> {
    List<AnswerOption> findByQuestion(Long questionId);
}
