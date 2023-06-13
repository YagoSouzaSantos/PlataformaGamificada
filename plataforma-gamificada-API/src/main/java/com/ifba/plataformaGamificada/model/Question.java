package com.ifba.plataformaGamificada.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long phase;
    @Lob
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<AnswerOption> answers;

    public Question(Long phaseId, String text) {
        this.phase = phaseId;
        this.text = text;
    }

    public Question() {

    }
}
