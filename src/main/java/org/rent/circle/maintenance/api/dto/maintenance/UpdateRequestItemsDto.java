package org.rent.circle.maintenance.api.dto.maintenance;

import jakarta.validation.constraints.NotNull;
import java.util.List;
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
public class UpdateRequestItemsDto {

    @NotNull
    private Long maintenanceRequestId;

    @NotNull
    private List<LaborDto> labors;

    @NotNull
    private List<BillableDto> billables;
}
