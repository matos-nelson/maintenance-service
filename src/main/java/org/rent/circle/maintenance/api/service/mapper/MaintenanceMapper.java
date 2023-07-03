package org.rent.circle.maintenance.api.service.mapper;

import org.mapstruct.Mapper;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@Mapper(componentModel = "cdi")
public interface MaintenanceMapper {

    MaintenanceRequest toModel(SaveMaintenanceRequestDto saveMaintenanceRequestDto);

    MaintenanceRequestDto toDto(MaintenanceRequest maintenanceRequest);
}
