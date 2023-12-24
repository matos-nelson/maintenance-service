package org.rent.circle.maintenance.api.dto.maintenance;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillableDto {

    @NotNull
    private Integer quantity;

    @NotNull
    private Double rate;

    @NotNull
    private String description;
}
