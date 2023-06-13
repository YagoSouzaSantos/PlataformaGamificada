package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.UsuarioDto;
import com.ifba.plataformaGamificada.controller.form.UsuarioForm;
import com.ifba.plataformaGamificada.model.Usuario;
import com.ifba.plataformaGamificada.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = IUsuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            UsuarioDto usuarioDto = UsuarioDto.converter(usuario);
            return ResponseEntity.ok(usuarioDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody UsuarioForm form) {
        Optional<Usuario> usuarioOptional = IUsuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setPontuacao(form.getPontuacao());
            usuario.setNivel(form.getNivel());
            IUsuarioRepository.save(usuario);

            UsuarioDto usuarioDto = UsuarioDto.converter(usuario);
            return ResponseEntity.ok(usuarioDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
