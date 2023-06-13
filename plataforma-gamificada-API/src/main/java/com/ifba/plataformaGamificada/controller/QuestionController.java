package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.AnswerDTO;
import com.ifba.plataformaGamificada.controller.dto.QuestionDTO;
import com.ifba.plataformaGamificada.model.AnswerOption;
import com.ifba.plataformaGamificada.model.Question;
import com.ifba.plataformaGamificada.repository.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/phase/{phaseId}/questions")
public class QuestionController {

    @Autowired
    private IQuestionRepository questionRepository;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestionsByPhase(@PathVariable Long phaseId) {
        try {
            List<Question> questions = questionRepository.findByPhase(phaseId);

            List<QuestionDTO> questionDTOs = new ArrayList<>();
            for (Question question : questions) {
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setText(question.getText());
                questionDTO.setPhase(question.getPhase());

                List<AnswerDTO> answerDTOs = new ArrayList<>();
                for (AnswerOption answer : question.getAnswers()) {
                    AnswerDTO answerDTO = new AnswerDTO();
                    answerDTO.setId(answer.getId());
                    answerDTO.setText(answer.getText());
                    answerDTO.setCorrect(answer.isCorrect());
                    answerDTOs.add(answerDTO);
                }
                questionDTO.setAnswers(answerDTOs);

                questionDTOs.add(questionDTO);
            }

            return ResponseEntity.ok(questionDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable Long phaseId) {
        try {
            Question question = new Question();
            question.setText(questionDTO.getText());
            question.setPhase(phaseId);


            List<AnswerOption> answers = new ArrayList<>();
            for (AnswerDTO answerDTO : questionDTO.getAnswers()) {
                AnswerOption answer = new AnswerOption();
                answer.setText(answerDTO.getText());
                answer.setCorrect(answerDTO.isCorrect());
                answer.setQuestion(question);
                answers.add(answer);
            }

            question.setAnswers(answers);

            question = questionRepository.save(question);

            QuestionDTO responseDTO = new QuestionDTO();
            responseDTO.setId(question.getId());
            responseDTO.setText(question.getText());

            List<AnswerDTO> responseAnswers = new ArrayList<>();
            for (AnswerOption answer : question.getAnswers()) {
                AnswerDTO answerDTO = new AnswerDTO();
                answerDTO.setId(answer.getId());
                answerDTO.setText(answer.getText());
                answerDTO.setCorrect(answer.isCorrect());
                responseAnswers.add(answerDTO);
            }
            responseDTO.setAnswers(responseAnswers);

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
