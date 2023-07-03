package org.rent.circle.maintenance.api.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateMaintenanceRequestDto;
import org.rent.circle.maintenance.api.enums.Status;
import org.rent.circle.maintenance.api.persistence.model.Category;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;
import org.rent.circle.maintenance.api.persistence.repository.CategoryRepository;
import org.rent.circle.maintenance.api.persistence.repository.MaintenanceRequestRepository;
import org.rent.circle.maintenance.api.service.mapper.MaintenanceMapper;

@QuarkusTest
public class MaintenanceServiceTest {

    @InjectMock
    MaintenanceRequestRepository maintenanceRequestRepository;

    @InjectMock
    CategoryRepository categoryRepository;

    @InjectMock
    MaintenanceMapper maintenanceMapper;

    @Inject
    MaintenanceService maintenanceService;

    @Test
    public void saveRequest_WhenGivenCategoryCantBeFound_ShouldReturnNull() {
        // Arrange
        SaveMaintenanceRequestDto saveMaintenanceRequestDto = SaveMaintenanceRequestDto.builder()
            .ownerId(1L)
            .residentId(2L)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .build();

        when(categoryRepository.findById(saveMaintenanceRequestDto.getCategoryId())).thenReturn(null);

        // Act
        Long result = maintenanceService.saveRequest(saveMaintenanceRequestDto);

        // Assert
        assertNull(result);
    }

    @Test
    public void saveRequest_WhenCalled_ShouldReturnRequestId() {
        // Arrange
        SaveMaintenanceRequestDto saveMaintenanceRequestDto = SaveMaintenanceRequestDto.builder()
            .ownerId(1L)
            .residentId(2L)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(123L);

        when(categoryRepository.findById(saveMaintenanceRequestDto.getCategoryId())).thenReturn(new Category());
        when(maintenanceMapper.toModel(saveMaintenanceRequestDto)).thenReturn(maintenanceRequest);

        // Act
        Long result = maintenanceService.saveRequest(saveMaintenanceRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(maintenanceRequest.getId(), result);
        assertNotNull(maintenanceRequest.getCategory());
    }

    @Test
    public void updateRequest_WhenGivenAStatus_ShouldUpdateStatus() {
        // Arrange
        Long maintenanceRequestId = 1L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .status(Status.REJECTED)
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(maintenanceRequestId);
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);

        when(maintenanceRequestRepository.findById(maintenanceRequestId)).thenReturn(maintenanceRequest);
        when(maintenanceMapper.toDto(Mockito.any())).thenReturn(new MaintenanceRequestDto());

        // Act
        MaintenanceRequestDto result = maintenanceService.updateRequest(updateMaintenanceRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(Status.REJECTED.value, maintenanceRequest.getStatus());
        Mockito.verify(maintenanceRequestRepository, times(1)).persist(maintenanceRequest);
        assertNull(maintenanceRequest.getCompletedAt());
    }

    @Test
    public void updateRequest_WhenMaintenanceRequestIsNotUpdatable_ShouldReturnNull() {
        // Arrange
        Long maintenanceRequestId = 1L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .status(Status.COMPLETED)
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(maintenanceRequestId);
        maintenanceRequest.setStatus(Status.COMPLETED.value);

        when(maintenanceRequestRepository.findById(maintenanceRequestId)).thenReturn(maintenanceRequest);

        // Act
        MaintenanceRequestDto result = maintenanceService.updateRequest(updateMaintenanceRequestDto);

        // Assert
        assertNull(result);
        Mockito.verify(maintenanceRequestRepository, times(0)).persist(maintenanceRequest);
    }

    @Test
    public void updateRequest_WhenGivenACompletedStatus_ShouldPopulateCompletedAt() {
        // Arrange
        Long maintenanceRequestId = 1L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .note("Completed Request")
            .status(Status.COMPLETED)
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(maintenanceRequestId);
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);

        when(maintenanceRequestRepository.findById(maintenanceRequestId)).thenReturn(maintenanceRequest);
        when(maintenanceMapper.toDto(Mockito.any())).thenReturn(new MaintenanceRequestDto());

        // Act
        MaintenanceRequestDto result = maintenanceService.updateRequest(updateMaintenanceRequestDto);

        // Assert
        assertNotNull(result);
        Mockito.verify(maintenanceRequestRepository, times(1)).persist(maintenanceRequest);
        assertEquals(updateMaintenanceRequestDto.getNote(), maintenanceRequest.getNote());
        assertEquals(Status.COMPLETED.value, maintenanceRequest.getStatus());
        assertNotNull(maintenanceRequest.getCompletedAt());
    }

    @Test
    public void updateRequest_WhenGivenMaintenanceIsNotFound_ShouldReturnNull() {
        // Arrange
        Long maintenanceRequestId = 1L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .status(Status.COMPLETED)
            .build();

        when(maintenanceRequestRepository.findById(maintenanceRequestId)).thenReturn(null);

        // Act
        assertDoesNotThrow(() -> {
            MaintenanceRequestDto result = maintenanceService.updateRequest(updateMaintenanceRequestDto);
            assertNull(result);
        });

        // Assert
        Mockito.verify(maintenanceRequestRepository, times(0)).persist((MaintenanceRequest) Mockito.any());
    }
}
