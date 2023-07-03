package org.rent.circle.maintenance.api.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateMaintenanceRequestDto;
import org.rent.circle.maintenance.api.service.MaintenanceService;

@AllArgsConstructor
@Path("/maintenance")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class MaintenanceResource {

    private final MaintenanceService maintenanceService;

    @POST
    @Path("/request")
    public Long saveMaintenanceRequest(@Valid SaveMaintenanceRequestDto saveMaintenanceRequestDto) {
        return maintenanceService.saveRequest(saveMaintenanceRequestDto);
    }

    @PATCH
    @Path("/request")
    public MaintenanceRequestDto updateMaintenanceRequest(
        @Valid UpdateMaintenanceRequestDto updateMaintenanceRequestDto) {
        return maintenanceService.updateRequest(updateMaintenanceRequestDto);
    }
}
