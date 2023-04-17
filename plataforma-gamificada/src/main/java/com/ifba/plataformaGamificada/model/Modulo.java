package com.ifba.plataformaGamificada.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_modulo;
    private String nome;
    private int id_nivel;

    public Modulo(Long id_modulo, String nome, int nivel) {
        this.id_modulo = id_modulo;
        this.nome = nome;
        this.id_nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modulo)) return false;
        Modulo modulo = (Modulo) o;
        return id_nivel == modulo.id_nivel && id_modulo.equals(modulo.id_modulo) && Objects.equals(nome, modulo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_modulo, nome, id_nivel);
    }

    public Long getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(Long id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }
}
