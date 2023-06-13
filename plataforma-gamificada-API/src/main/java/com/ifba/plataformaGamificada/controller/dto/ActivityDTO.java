package com.ifba.plataformaGamificada.controller.dto;

import com.ifba.plataformaGamificada.model.Activity;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class ActivityDTO {

    private Long id;
    private Long phase;
    private String title;

    public ActivityDTO(Activity activity) {
        this.id = activity.getId();
        this.phase = activity.getPhase();
        this.title = activity.getTitle();
    }

    public ActivityDTO() {

    }

    public static Page<ActivityDTO> toConvert(Page<Activity> activities) {
        return activities.map(ActivityDTO::new);
    }

}
