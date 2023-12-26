package org.rent.circle.maintenance.api.service.mapper;

import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateRequestItemsDto;
import org.rent.circle.maintenance.api.persistence.model.Billable;
import org.rent.circle.maintenance.api.persistence.model.Labor;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface MaintenanceMapper {

    MaintenanceRequest toModel(SaveMaintenanceRequestDto saveMaintenanceRequestDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "updatedAt", source = "updatedAt")
    MaintenanceRequestDto toDto(MaintenanceRequest maintenanceRequest);

    List<MaintenanceRequestDto> toDtoList(List<MaintenanceRequest> maintenanceRequests);

    void updateRequestItems(UpdateRequestItemsDto updateRequestItems,
        @MappingTarget MaintenanceRequest maintenanceRequest);

    @AfterMapping
    default void afterMapping(@MappingTarget MaintenanceRequest target) {
        List<Labor> labors = target.getLabors();
        if (labors == null || labors.isEmpty()) {
            return;
        }
        labors.forEach(l -> l.setMaintenanceRequestId(target.getId()));

        List<Billable> billables = target.getBillables();
        if (billables == null || billables.isEmpty()) {
            return;
        }
        billables.forEach(l -> l.setMaintenanceRequestId(target.getId()));
    }
}
