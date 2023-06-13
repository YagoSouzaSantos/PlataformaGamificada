package com.ifba.plataformaGamificada.controller.dto;

import com.ifba.plataformaGamificada.model.WorldsWizard;
import lombok.Data;

@Data
public class WorldsWizardDTO {

    public Long id;
    public String title;
    public String message;

    public WorldsWizardDTO(WorldsWizard wizard) {
        this.id = wizard.getId();
        this.title = wizard.getTitle();
        this.message = wizard.getMessage();
    }
}
