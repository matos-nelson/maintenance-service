package org.rent.circle.maintenance.api.dto.maintenance;

import java.time.LocalDateTime;
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
public class MaintenanceRequestDto {

    private Long id;
    private Long ownerId;
    private Long residentId;
    private Long propertyId;
    private Long categoryId;
    private String description;
    private String status;
    private LocalDateTime completedAt;
}
