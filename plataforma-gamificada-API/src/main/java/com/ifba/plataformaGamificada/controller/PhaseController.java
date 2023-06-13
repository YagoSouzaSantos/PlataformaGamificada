package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.PhaseDTO;
import com.ifba.plataformaGamificada.controller.form.PhaseForm;
import com.ifba.plataformaGamificada.model.Phase;
import com.ifba.plataformaGamificada.repository.IPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/phase")
public class PhaseController {

    @Autowired
    private IPhaseRepository phaseRepository;

    @GetMapping
    @Cacheable(value = "listaDeFases")
    public Page<PhaseDTO> lista(Pageable paginacao) {

            Page<Phase> phases = phaseRepository.findAll(paginacao);

            return PhaseDTO.toConvert(phases);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeFases", allEntries = true)
    public ResponseEntity<PhaseDTO> cadastrar(@RequestBody @Valid PhaseForm form, UriComponentsBuilder uriBuilder) {
        Phase phase = form.toConvert();
        phaseRepository.save(phase);

        URI uri = uriBuilder.path("/phase/{id}").buildAndExpand(phase.getId()).toUri();

        return ResponseEntity.created(uri).body(new PhaseDTO(phase));
    }

    @GetMapping("/{world}")
    public Page<PhaseDTO> getByWorld(@PathVariable int world, Pageable pageable) {
        Page<Phase> phases = phaseRepository.findByWorld(world, pageable);
        return phases.map(PhaseDTO::new);
    }


}
