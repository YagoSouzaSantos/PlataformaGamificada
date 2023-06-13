package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.model.AnswerOption;
import com.ifba.plataformaGamificada.repository.IAnswerOptionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions/{questionId}/answers")
public class AnswerOptionController {

    private IAnswerOptionRepository answerOptionRepository;

    @GetMapping
    public List<AnswerOption> getAnswersByQuestion(@PathVariable Long questionId) {
        return answerOptionRepository.findByQuestion(questionId);
    }
}
