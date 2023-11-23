package org.rent.circle.maintenance.api.dto.maintenance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rent.circle.maintenance.api.enums.Status;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequestDto {

    @NotNull
    private Long maintenanceRequestId;

    @NotBlank
    private String ownerId;

    private String note;

    @NotNull
    private Status status;
}
