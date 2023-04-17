package com.ifba.plataformaGamificada.controller.dto;

import com.ifba.plataformaGamificada.model.Phase;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PhaseDTO {

    public Long id;
    public Long world;
    public String title;

    public PhaseDTO(Phase phase) {
        this.id = phase.getId();
        this.world = phase.getWorld();
        this.title = phase.getTitle();
    }

    public static Page<PhaseDTO> toConvert(Page<Phase> phases) {
        return phases.map(PhaseDTO::new);
    }
}
