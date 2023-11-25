package org.rent.circle.maintenance.api.resource;

import io.quarkus.security.Authenticated;
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
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateMaintenanceRequestDto;
import org.rent.circle.maintenance.api.service.MaintenanceService;

@AllArgsConstructor
@Authenticated
@Path("/maintenance/request")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class MaintenanceResource {

    private final MaintenanceService maintenanceService;
    private final JsonWebToken jwt;

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
    @Path("/{id}")
    public MaintenanceRequestDto getMaintenanceRequest(@PathParam("id") Long maintenanceRequestId) {
        return maintenanceService.getRequest(maintenanceRequestId, jwt.getName());
    }

    @GET
    public List<MaintenanceRequestDto> getMaintenanceRequests(
        @QueryParam("page") @NotNull @Min(0) Integer page,
        @QueryParam("pageSize") @NotNull @Min(1) Integer pageSize) {
        return maintenanceService.getRequests(jwt.getName(), page, pageSize);
    }
}
