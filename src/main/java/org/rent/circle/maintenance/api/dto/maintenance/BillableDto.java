package org.rent.circle.maintenance.api.dto.maintenance;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillableDto {

    private Integer quantity;
    private Double rate;
    private String description;
}
