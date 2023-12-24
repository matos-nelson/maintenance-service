package org.rent.circle.maintenance.api.dto.maintenance;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaborDto {

    @NotNull
    private LocalDate workCompletedAt;

    @NotNull
    private Float hours;

    @NotNull
    private String description;
}
