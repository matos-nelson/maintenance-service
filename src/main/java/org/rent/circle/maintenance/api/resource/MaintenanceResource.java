package org.rent.circle.maintenance.api.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.service.MaintenanceService;

@AllArgsConstructor
@Path("/maintenance")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MaintenanceResource {

    private final MaintenanceService maintenanceService;

    @POST
    @Path("/request")
    public Long saveMaintenanceRequest(@Valid SaveMaintenanceRequestDto saveMaintenanceRequestDto) {
        return maintenanceService.saveRequest(saveMaintenanceRequestDto);
    }
}
