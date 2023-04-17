package com.ifba.plataformaGamificada.controller.form;

import com.ifba.plataformaGamificada.model.Phase;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
public class PhaseForm {

    @NonNull
    @NotEmpty
    private String world;
    @NonNull
    @NotEmpty
    @Length(min = 10)
    private String title;

    public Phase toConvert( ) {
        return new Phase(world, title);
    }

}
