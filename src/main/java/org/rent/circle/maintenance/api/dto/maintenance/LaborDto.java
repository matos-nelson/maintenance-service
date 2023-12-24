package org.rent.circle.maintenance.api.dto.maintenance;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaborDto {

    @NotNull
    private LocalDateTime workDate;

    @NotNull
    private Float hours;

    @NotNull
    private String description;
}
