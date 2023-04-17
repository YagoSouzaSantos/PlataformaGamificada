package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.ModuloDto;
import com.ifba.plataformaGamificada.model.Modulo;
import com.ifba.plataformaGamificada.repository.IModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/modulos")
public class ModuloController {

    @Autowired
    private IModuloRepository iModuloRepository;

    @GetMapping
    public List<ModuloDto> lista() {
        List<Modulo> modulos = iModuloRepository.findAll();
        return ModuloDto.converter(modulos);
    }

}
