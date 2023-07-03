package org.rent.circle.maintenance.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.enums.Status;
import org.rent.circle.maintenance.api.persistence.model.Category;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@QuarkusTest
public class MaintenanceMapperTest {

    @Inject
    MaintenanceMapper maintenanceMapper;

    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        MaintenanceRequest result = maintenanceMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenASaveMaintenanceRequestDto_ShouldMap() {
        // Arrange
        SaveMaintenanceRequestDto saveMaintenanceRequestDto = SaveMaintenanceRequestDto.builder()
            .ownerId(1L)
            .residentId(2L)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .build();

        // Act
        MaintenanceRequest result = maintenanceMapper.toModel(saveMaintenanceRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(saveMaintenanceRequestDto.getOwnerId(), result.getOwnerId());
        assertEquals(saveMaintenanceRequestDto.getResidentId(), result.getResidentId());
        assertEquals(saveMaintenanceRequestDto.getPropertyId(), result.getPropertyId());
        assertEquals(saveMaintenanceRequestDto.getDescription(), result.getDescription());
    }

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        MaintenanceRequestDto result = maintenanceMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAValidMaintenanceRequest_ShouldReturnDto() {
        // Arrange
        Category category = new Category();
        category.setId(100L);
        category.setName("My Category");

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(1L);
        maintenanceRequest.setOwnerId(2L);
        maintenanceRequest.setResidentId(3L);
        maintenanceRequest.setPropertyId(4L);
        maintenanceRequest.setCategory(category);
        maintenanceRequest.setDescription("My Description");
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);

        // Act
        MaintenanceRequestDto result = maintenanceMapper.toDto(maintenanceRequest);

        // Assert
        assertNotNull(result);
        assertEquals(maintenanceRequest.getOwnerId(), result.getOwnerId());
        assertEquals(maintenanceRequest.getResidentId(), result.getResidentId());
        assertEquals(maintenanceRequest.getPropertyId(), result.getPropertyId());
        assertEquals(maintenanceRequest.getDescription(), result.getDescription());
        assertEquals(maintenanceRequest.getStatus(), result.getStatus());
        assertEquals(maintenanceRequest.getCategory().getId(), result.getCategory().getId());
        assertEquals(maintenanceRequest.getCategory().getName(), result.getCategory().getName());
    }
}
