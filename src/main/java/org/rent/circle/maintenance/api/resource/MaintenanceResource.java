package org.rent.circle.maintenance.api.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateMaintenanceRequestDto;
import org.rent.circle.maintenance.api.service.MaintenanceService;

@AllArgsConstructor
@Path("/maintenance/request")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class MaintenanceResource {

    private final MaintenanceService maintenanceService;

    @POST
    public Long saveMaintenanceRequest(@Valid SaveMaintenanceRequestDto saveMaintenanceRequestDto) {
        return maintenanceService.saveRequest(saveMaintenanceRequestDto);
    }

    @PATCH
    public MaintenanceRequestDto updateMaintenanceRequest(
        @Valid UpdateMaintenanceRequestDto updateMaintenanceRequestDto) {
        return maintenanceService.updateRequest(updateMaintenanceRequestDto);
    }

    @GET
    @Path("/{id}/manager/{managerId}")
    public MaintenanceRequestDto getMaintenanceRequest(@PathParam("id") Long maintenanceRequestId,
        @PathParam("managerId") String managerId) {
        return maintenanceService.getRequest(maintenanceRequestId, managerId);
    }

    @GET
    @Path("/manager/{managerId}")
    public List<MaintenanceRequestDto> getMaintenanceRequests(@PathParam("managerId") String managerId,
        @QueryParam("page") @NotNull @Min(0) Integer page,
        @QueryParam("pageSize") @NotNull @Min(1) Integer pageSize) {
        return maintenanceService.getRequests(managerId, page, pageSize);
    }
}
