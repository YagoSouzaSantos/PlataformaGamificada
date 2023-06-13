package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.services.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private Disc disc;

    @PostMapping
    public void cadastrarImagem(@RequestParam MultipartFile foto){
        disc.salvarFoto(foto);
    }
}
