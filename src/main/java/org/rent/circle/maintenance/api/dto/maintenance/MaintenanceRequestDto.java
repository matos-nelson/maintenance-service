package org.rent.circle.maintenance.api.dto.maintenance;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;

@Getter
@Setter
public class MaintenanceRequestDto {

    private Long ownerId;
    private Long residentId;
    private Long propertyId;
    private CategoryDto category;
    private String description;
    private String status;
    private LocalDateTime completedAt;
}