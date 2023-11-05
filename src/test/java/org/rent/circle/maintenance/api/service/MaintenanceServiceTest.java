package org.rent.circle.maintenance.api.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
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
        Long ownerId = 1L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .ownerId(ownerId)
            .status(Status.REJECTED)
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(maintenanceRequestId);
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);

        when(maintenanceRequestRepository.findByIdAndOwnerId(maintenanceRequestId, ownerId))
            .thenReturn(maintenanceRequest);
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
        Long ownerId = 2L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .ownerId(ownerId)
            .status(Status.COMPLETED)
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(maintenanceRequestId);
        maintenanceRequest.setStatus(Status.COMPLETED.value);

        when(maintenanceRequestRepository.findByIdAndOwnerId(maintenanceRequestId, ownerId))
            .thenReturn(maintenanceRequest);

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
        Long ownerId = 2L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .ownerId(ownerId)
            .note("Completed Request")
            .status(Status.COMPLETED)
            .build();

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(maintenanceRequestId);
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);

        when(maintenanceRequestRepository.findByIdAndOwnerId(maintenanceRequestId, ownerId))
            .thenReturn(maintenanceRequest);
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
    public void updateRequest_WhenGivenMaintenanceIdIsNotFound_ShouldReturnNull() {
        // Arrange
        Long maintenanceRequestId = 1L;
        Long ownerId = 2L;
        UpdateMaintenanceRequestDto updateMaintenanceRequestDto = UpdateMaintenanceRequestDto.builder()
            .maintenanceRequestId(maintenanceRequestId)
            .ownerId(ownerId)
            .status(Status.COMPLETED)
            .build();

        when(maintenanceRequestRepository.findByIdAndOwnerId(maintenanceRequestId, ownerId))
            .thenReturn(null);

        // Act
        assertDoesNotThrow(() -> {
            MaintenanceRequestDto result = maintenanceService.updateRequest(updateMaintenanceRequestDto);
            assertNull(result);
        });

        // Assert
        Mockito.verify(maintenanceRequestRepository, times(0)).persist((MaintenanceRequest) Mockito.any());
    }

    @Test
    public void getRequest_WhenMaintenanceWithGivenIdsAreNotFound_ShouldReturnNull() {
        // Arrange
        Long maintenanceRequestId = 1L;
        Long ownerId = 2L;
        when(maintenanceRequestRepository.findByIdAndOwnerId(maintenanceRequestId, ownerId)).thenReturn(null);

        // Act
        MaintenanceRequestDto result = maintenanceService.getRequest(maintenanceRequestId, ownerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getRequest_WhenMaintenanceWithGivenIdsAreFound_ShouldReturnMaintenanceRequest() {
        // Arrange
        Long maintenanceRequestId = 1L;
        Long ownerId = 2L;
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(maintenanceRequestId);

        when(maintenanceRequestRepository.findByIdAndOwnerId(maintenanceRequestId, ownerId))
            .thenReturn(new MaintenanceRequest());
        when(maintenanceMapper.toDto(maintenanceRequest)).thenReturn(new MaintenanceRequestDto());

        // Act
        MaintenanceRequestDto result = maintenanceService.getRequest(maintenanceRequestId, ownerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getRequests_WhenMaintenanceWithGivenIdsAreNotFound_ShouldReturnEmptyList() {
        // Arrange
        Long ownerId = 1L;
        int page = 2;
        int pageSize = 10;

        when(maintenanceRequestRepository.findMaintenanceRequests(ownerId, page, pageSize)).thenReturn(null);

        // Act
        List<MaintenanceRequestDto> result = maintenanceService.getRequests(ownerId, page, pageSize);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void getRequests_WhenMaintenanceWithGivenIdsAreFound_ShouldReturnList() {
        // Arrange
        Long ownerId = 1L;
        int page = 2;
        int pageSize = 10;
        List<MaintenanceRequest> maintenanceRequests = Collections.singletonList(new MaintenanceRequest());
        when(maintenanceRequestRepository.findMaintenanceRequests(ownerId, page, pageSize)).thenReturn(
            maintenanceRequests);
        when(maintenanceMapper.toDtoList(maintenanceRequests)).thenReturn(
            Collections.singletonList(new MaintenanceRequestDto()));

        // Act
        List<MaintenanceRequestDto> result = maintenanceService.getRequests(ownerId, page, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
