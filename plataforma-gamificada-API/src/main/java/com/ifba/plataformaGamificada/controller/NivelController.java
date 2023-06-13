package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.NivelDto;
import com.ifba.plataformaGamificada.model.Nivel;
import com.ifba.plataformaGamificada.repository.INivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/niveis")
public class NivelController {

    @Autowired
    private INivelRepository iNivelRepository;

    @GetMapping
    public List<NivelDto> lista() {
        List<Nivel> niveis = iNivelRepository.findAll();
        return NivelDto.converter(niveis);
    }





}
