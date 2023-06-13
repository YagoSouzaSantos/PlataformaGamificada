package com.ifba.plataformaGamificada.controller.form;

import com.ifba.plataformaGamificada.model.Topico;
import com.ifba.plataformaGamificada.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;

public class AtualizacaoTopicoForm {

    @NonNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NonNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;

    @NonNull
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NonNull String titulo) {
        this.titulo = titulo;
    }

    @NonNull
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(@NonNull String mensagem) {
        this.mensagem = mensagem;
    }


    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;


    }
}
