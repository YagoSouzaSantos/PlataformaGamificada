package com.ifba.plataformaGamificada.controller.dto;

import com.ifba.plataformaGamificada.model.Nivel;
import com.ifba.plataformaGamificada.model.Usuario;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class UsuarioDto {

    private String nome;
    private String email;
    private String nomeImagem;
    private Long tipoUsuario;

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.nomeImagem = usuario.getNomeImagem();
        this.tipoUsuario = usuario.getTipoUsuario();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios)  {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
