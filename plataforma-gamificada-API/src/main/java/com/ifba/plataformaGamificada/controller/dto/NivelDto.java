package com.ifba.plataformaGamificada.controller.dto;

import com.ifba.plataformaGamificada.model.Nivel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class NivelDto {

    private Long id;
    private long nivel;
    private String titulo;
    private String descricao;

    public NivelDto(Nivel nivel) {
        this.id = nivel.getId();
        this.nivel = nivel.getNivel();
        this.titulo = nivel.getTitulo();
        this.descricao = nivel.getDescricao();
    }

    public static List<NivelDto> converter(List<Nivel> niveis) {
        return niveis.stream().map(NivelDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getNivel() {
        return nivel;
    }
}
