package org.rent.circle.maintenance.api.dto.maintenance;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaborDto {

    private LocalDateTime workDate;
    private Float hours;
    private String description;
}
