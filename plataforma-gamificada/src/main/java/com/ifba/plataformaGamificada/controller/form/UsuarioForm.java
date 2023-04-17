package com.ifba.plataformaGamificada.controller.form;
import com.ifba.plataformaGamificada.model.Usuario;
import lombok.Data;

@Data
public class UsuarioForm {
    private String nome;
    private String email;
    private String senha;
    private String nomeImagem;
    private Long tipoUsuario;

    public Usuario converter() {
        return new Usuario(nome,email,senha,nomeImagem,tipoUsuario);
    }
}
