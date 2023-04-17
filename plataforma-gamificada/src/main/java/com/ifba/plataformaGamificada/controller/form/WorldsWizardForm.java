package com.ifba.plataformaGamificada.controller.form;

import com.ifba.plataformaGamificada.model.Usuario;
import com.ifba.plataformaGamificada.model.WorldsWizard;
import lombok.Data;

@Data
public class WorldsWizardForm {
    private Long id;
    private String title;
    private String message;

    public WorldsWizard converter() {
        return new WorldsWizard(id,title,message);
    }
}
