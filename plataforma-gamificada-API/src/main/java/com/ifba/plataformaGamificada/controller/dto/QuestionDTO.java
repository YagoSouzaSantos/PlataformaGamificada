package com.ifba.plataformaGamificada.controller.dto;

import lombok.Data;

import javax.persistence.Lob;
import java.util.List;

@Data
public class QuestionDTO {

    private Long id;
    private Long phase;
    @Lob
    private String text;
    private List<AnswerDTO> answers;

    public QuestionDTO(Long phase, String text, List<AnswerDTO> answers) {
        this.phase = phase;
        this.text = text;
        this.answers = answers;
    }

    public QuestionDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhase() {
        return phase;
    }

    public void setPhase(Long phase) {
        this.phase = phase;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    //  public static Page<ActivityDTO> toConvert(Page<Activity> activities) {
    //      return activities.map(ActivityDTO::new);
    //  }

}
