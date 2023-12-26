package org.rent.circle.maintenance.api.dto.maintenance;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;

@Getter
@Setter
public class MaintenanceRequestDto {

    private Long id;
    private Long residentId;
    private Long propertyId;
    private CategoryDto category;
    private String description;
    private String note;
    private String instructions;
    private String status;
    private LocalDateTime completedAt;
    private LocalDateTime updatedAt;
    private List<LaborDto> labors;
    private List<BillableDto> billables;
}