package org.rent.circle.maintenance.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.enums.Status;
import org.rent.circle.maintenance.api.persistence.model.Billable;
import org.rent.circle.maintenance.api.persistence.model.Category;
import org.rent.circle.maintenance.api.persistence.model.Labor;
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
            .residentId(2L)
            .propertyId(3L)
            .categoryId(4L)
            .description("description")
            .instructions("instructions")
            .build();

        // Act
        MaintenanceRequest result = maintenanceMapper.toModel(saveMaintenanceRequestDto);

        // Assert
        assertNotNull(result);
        assertEquals(saveMaintenanceRequestDto.getResidentId(), result.getResidentId());
        assertEquals(saveMaintenanceRequestDto.getPropertyId(), result.getPropertyId());
        assertEquals(saveMaintenanceRequestDto.getDescription(), result.getDescription());
        assertEquals(saveMaintenanceRequestDto.getInstructions(), result.getInstructions());
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

        Labor labor = new Labor();
        labor.setId(200L);
        labor.setWorkCompletedAt(LocalDateTime.now());
        labor.setHours(12.5F);
        labor.setMaintenanceRequestId(1L);
        labor.setDescription("My Labor Item");

        Billable billable = new Billable();
        billable.setId(300L);
        billable.setDescription("My Billable Item");
        billable.setRate(123.11);
        billable.setQuantity(10);
        billable.setMaintenanceRequestId(1L);

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(1L);
        maintenanceRequest.setManagerId("2");
        maintenanceRequest.setResidentId(3L);
        maintenanceRequest.setPropertyId(4L);
        maintenanceRequest.setCategory(category);
        maintenanceRequest.setDescription("My Description");
        maintenanceRequest.setInstructions("My Instructions");
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);
        maintenanceRequest.setUpdatedAt(LocalDateTime.now());
        maintenanceRequest.setLabors(Collections.singletonList(labor));
        maintenanceRequest.setBillables(Collections.singletonList(billable));

        // Act
        MaintenanceRequestDto result = maintenanceMapper.toDto(maintenanceRequest);

        // Assert
        assertNotNull(result);
        assertEquals(maintenanceRequest.getId(), result.getId());
        assertEquals(maintenanceRequest.getResidentId(), result.getResidentId());
        assertEquals(maintenanceRequest.getPropertyId(), result.getPropertyId());
        assertEquals(maintenanceRequest.getDescription(), result.getDescription());
        assertEquals(maintenanceRequest.getInstructions(), result.getInstructions());
        assertEquals(maintenanceRequest.getNote(), result.getNote());
        assertEquals(maintenanceRequest.getStatus(), result.getStatus());
        assertEquals(maintenanceRequest.getCategory().getId(), result.getCategory().getId());
        assertEquals(maintenanceRequest.getCategory().getName(), result.getCategory().getName());
        assertEquals(maintenanceRequest.getUpdatedAt(), result.getUpdatedAt());
        assertEquals(1, maintenanceRequest.getLabors().size());
        assertEquals(labor, maintenanceRequest.getLabors().get(0));
        assertEquals(1, maintenanceRequest.getBillables().size());
        assertEquals(billable, maintenanceRequest.getBillables().get(0));
    }

    @Test
    public void toDtoList_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        List<MaintenanceRequestDto> result = maintenanceMapper.toDtoList(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDtoList_WhenGivenValidMaintenanceRequests_ShouldReturnDtoList() {
        // Arrange
        Category category = new Category();
        category.setId(100L);
        category.setName("My Category");

        Labor labor = new Labor();
        labor.setId(200L);
        labor.setWorkCompletedAt(LocalDateTime.now());
        labor.setHours(12.5F);
        labor.setMaintenanceRequestId(1L);
        labor.setDescription("My Labor Item");

        Billable billable = new Billable();
        billable.setId(300L);
        billable.setDescription("My Billable Item");
        billable.setRate(123.11);
        billable.setQuantity(10);
        billable.setMaintenanceRequestId(1L);

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(1L);
        maintenanceRequest.setManagerId("2");
        maintenanceRequest.setResidentId(3L);
        maintenanceRequest.setPropertyId(4L);
        maintenanceRequest.setCategory(category);
        maintenanceRequest.setDescription("My Description");
        maintenanceRequest.setInstructions("My Instructions");
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);
        maintenanceRequest.setUpdatedAt(LocalDateTime.now());
        maintenanceRequest.setLabors(Collections.singletonList(labor));
        maintenanceRequest.setBillables(Collections.singletonList(billable));

        // Act
        List<MaintenanceRequestDto> result = maintenanceMapper.toDtoList(Collections.singletonList(maintenanceRequest));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(maintenanceRequest.getId(), result.get(0).getId());
        assertEquals(maintenanceRequest.getResidentId(), result.get(0).getResidentId());
        assertEquals(maintenanceRequest.getPropertyId(), result.get(0).getPropertyId());
        assertEquals(maintenanceRequest.getDescription(), result.get(0).getDescription());
        assertEquals(maintenanceRequest.getInstructions(), result.get(0).getInstructions());
        assertEquals(maintenanceRequest.getNote(), result.get(0).getNote());
        assertEquals(maintenanceRequest.getStatus(), result.get(0).getStatus());
        assertEquals(maintenanceRequest.getCategory().getId(), result.get(0).getCategory().getId());
        assertEquals(maintenanceRequest.getCategory().getName(), result.get(0).getCategory().getName());
        assertEquals(maintenanceRequest.getUpdatedAt(), result.get(0).getUpdatedAt());
        assertEquals(1, maintenanceRequest.getLabors().size());
        assertEquals(labor, maintenanceRequest.getLabors().get(0));
        assertEquals(1, maintenanceRequest.getBillables().size());
        assertEquals(billable, maintenanceRequest.getBillables().get(0));
    }
}
