package org.rent.circle.maintenance.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
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
        assertEquals(saveMaintenanceRequestDto.getCategoryId(), result.getCategoryId());
        assertEquals(saveMaintenanceRequestDto.getDescription(), result.getDescription());
    }
}
