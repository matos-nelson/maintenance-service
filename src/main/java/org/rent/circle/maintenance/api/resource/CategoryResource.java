package org.rent.circle.maintenance.api.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.AllArgsConstructor;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;
import org.rent.circle.maintenance.api.service.CategoryService;

@AllArgsConstructor
@Path("/maintenance/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    private final CategoryService categoryService;

    @GET
    public List<CategoryDto> getMaintenanceCategories() {
        return categoryService.getCategories();
    }

}
