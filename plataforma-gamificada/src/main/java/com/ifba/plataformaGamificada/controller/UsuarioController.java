package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.UsuarioDto;
import com.ifba.plataformaGamificada.controller.form.UsuarioForm;
import com.ifba.plataformaGamificada.model.Usuario;
import com.ifba.plataformaGamificada.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioRepository IUsuarioRepository;

    @GetMapping
    public List<UsuarioDto> lista() {
        List<Usuario> usuarios = IUsuarioRepository.findAll();
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    public void cadastrar(@RequestBody UsuarioForm form){
        Usuario usuario = form.converter();
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        IUsuarioRepository.save(usuario);
    }


}
