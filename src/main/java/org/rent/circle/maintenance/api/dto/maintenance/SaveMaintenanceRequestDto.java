package org.rent.circle.maintenance.api.dto.maintenance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveMaintenanceRequestDto {

    @NotNull
    private Long residentId;

    @NotNull
    private Long propertyId;

    @NotNull
    private Long categoryId;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String instructions;
}
