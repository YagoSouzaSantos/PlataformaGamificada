package com.ifba.plataformaGamificada.controller.dto;

import com.ifba.plataformaGamificada.model.Modulo;


import java.util.List;
import java.util.stream.Collectors;

public class ModuloDto {

    private long id_modulo;
    private String nome;
    private int id_nivel;

    public ModuloDto(Modulo modulo) {
        this.id_modulo = modulo.getId_modulo();
        this.nome = modulo.getNome();
        this.id_nivel = modulo.getId_nivel();
    }

    public static List<ModuloDto> converter(List<Modulo> modulos) {
        return modulos.stream().map(ModuloDto::new).collect(Collectors.toList());
    }

    public long getId_modulo() {
        return id_modulo;
    }

    public String getNome() {
        return nome;
    }

    public int getId_nivel() {
        return id_nivel;
    }
}
