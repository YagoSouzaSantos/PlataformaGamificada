package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.config.security.TokenService;
import com.ifba.plataformaGamificada.controller.dto.TokenDto;
import com.ifba.plataformaGamificada.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-access-token", token);
            System.out.println(token);

            return new ResponseEntity<TokenDto>(new TokenDto(token,"Bearer"),headers, HttpStatus.OK);

        } catch (AuthenticationException e) {

            return ResponseEntity.badRequest().build();
        }
        
    }
}
