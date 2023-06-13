package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.WorldsWizardDTO;
import com.ifba.plataformaGamificada.model.WorldsWizard;
import com.ifba.plataformaGamificada.repository.IWorldsWizardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/worldswizard")
public class WorldsWizardController {

    @Autowired
    private IWorldsWizardRepository iWorldsWizardRepository;


    @GetMapping("/{title}")
    public ResponseEntity<WorldsWizardDTO> worldsWizardmessage(@PathVariable String title) {
        Optional<WorldsWizard> worldsWizardOptional = iWorldsWizardRepository.findByTitle(title);
        if (worldsWizardOptional.isPresent()) {
            return ResponseEntity.ok(new WorldsWizardDTO(worldsWizardOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

}
