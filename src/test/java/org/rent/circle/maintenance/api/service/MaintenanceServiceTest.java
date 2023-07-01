package org.rent.circle.maintenance.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;
import org.rent.circle.maintenance.api.persistence.repository.MaintenanceRequestRepository;
import org.rent.circle.maintenance.api.service.mapper.MaintenanceMapper;

@QuarkusTest
public class MaintenanceServiceTest {

    @InjectMock
    MaintenanceRequestRepository maintenanceRequestRepository;

    @InjectMock
    MaintenanceMapper maintenanceMapper;

    @Inject
    MaintenanceService maintenanceService;

    @Test
    public void saveRequest_WhenCalled_ShouldReturnRequestId() {
        // Arrange
        SaveMaintenanceRequestDto saveMaintenanceRequestDto = SaveMaintenanceRequestDto.builder()
            .ownerId(1L)
            .residentId(2L)
            .propertyId(3L)
            .categoryId(3L)
            .description("description")
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(123L);

        when(maintenanceMapper.toModel(saveMaintenanceRequestDto)).thenReturn(maintenanceRequest);

        // Act
        Long result = maintenanceService.saveRequest(saveMaintenanceRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(maintenanceRequest.getId(), result);
    }
}
