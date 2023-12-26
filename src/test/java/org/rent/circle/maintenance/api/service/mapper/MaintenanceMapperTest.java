package org.rent.circle.maintenance.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.maintenance.BillableDto;
import org.rent.circle.maintenance.api.dto.maintenance.LaborDto;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateRequestItemsDto;
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
    public void toDto_WhenGivenAMaintenanceRequest_ShouldReturnDto() {
        // Arrange
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(1L);
        maintenanceRequest.setManagerId("2");
        maintenanceRequest.setResidentId(3L);
        maintenanceRequest.setPropertyId(4L);
        maintenanceRequest.setDescription("My Description");
        maintenanceRequest.setInstructions("My Instructions");
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);
        maintenanceRequest.setUpdatedAt(LocalDateTime.now());

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
        assertNull(result.getCategory());
        assertEquals(maintenanceRequest.getUpdatedAt(), result.getUpdatedAt());
    }

    @Test
    public void toDto_WhenGivenAValidMaintenanceRequest_ShouldReturnDto() {
        // Arrange
        Category category = new Category();
        category.setId(100L);
        category.setName("My Category");

        Labor labor = new Labor();
        labor.setId(200L);
        labor.setWorkCompletedAt(LocalDate.now());
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
        assertEquals(1, result.getLabors().size());
        assertEquals(1, result.getBillables().size());
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
    public void toDtoList_WhenGivenMaintenanceRequests_ShouldReturnDtoList() {
        // Arrange
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(1L);
        maintenanceRequest.setManagerId("2");
        maintenanceRequest.setResidentId(3L);
        maintenanceRequest.setPropertyId(4L);
        maintenanceRequest.setDescription("My Description");
        maintenanceRequest.setInstructions("My Instructions");
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);
        maintenanceRequest.setUpdatedAt(LocalDateTime.now());

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
        assertNull(result.get(0).getCategory());
        assertEquals(maintenanceRequest.getUpdatedAt(), result.get(0).getUpdatedAt());
    }

    @Test
    public void toDtoList_WhenGivenValidMaintenanceRequests_ShouldReturnDtoList() {
        // Arrange
        Category category = new Category();
        category.setId(100L);
        category.setName("My Category");

        Labor labor = new Labor();
        labor.setId(200L);
        labor.setWorkCompletedAt(LocalDate.now());
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
        assertEquals(1, result.get(0).getLabors().size());
        assertEquals(1, result.get(0).getBillables().size());
    }

    @Test
    public void updateRequestItems_WhenGivenNullUpdateRequestItemsDto_ShouldReturnNull() {
        // Arrange
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(1L);
        maintenanceRequest.setManagerId("2");
        maintenanceRequest.setResidentId(3L);
        maintenanceRequest.setPropertyId(4L);
        maintenanceRequest.setDescription("My Description");
        maintenanceRequest.setInstructions("My Instructions");
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);
        maintenanceRequest.setUpdatedAt(LocalDateTime.now());

        // Act
        maintenanceMapper.updateRequestItems(null, maintenanceRequest);

        // Assert
        assertNotNull(maintenanceRequest);
    }

    @Test
    public void updateVendor_WhenGivenAnUpdateVendorDto_ShouldMap() {
        // Arrange
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setId(1L);
        maintenanceRequest.setManagerId("2");
        maintenanceRequest.setResidentId(3L);
        maintenanceRequest.setPropertyId(4L);
        maintenanceRequest.setDescription("My Description");
        maintenanceRequest.setInstructions("My Instructions");
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);
        maintenanceRequest.setUpdatedAt(LocalDateTime.now());

        LaborDto laborDto = LaborDto.builder()
            .hours(1F)
            .workCompletedAt(LocalDate.now())
            .description("Labor")
            .build();

        BillableDto billableDto = BillableDto.builder()
            .rate(123D)
            .quantity(10)
            .description("Billable")
            .build();

        UpdateRequestItemsDto updateRequestItems = UpdateRequestItemsDto.builder()
            .maintenanceRequestId(maintenanceRequest.getId())
            .labors(Collections.singletonList(laborDto))
            .billables(Collections.singletonList(billableDto))
            .build();

        // Act
        maintenanceMapper.updateRequestItems(updateRequestItems, maintenanceRequest);

        // Assert
        assertNotNull(maintenanceRequest);
        assertEquals(maintenanceRequest.getId(), maintenanceRequest.getLabors().get(0).getMaintenanceRequestId());
        assertEquals(updateRequestItems.getLabors().get(0).getHours(),
            maintenanceRequest.getLabors().get(0).getHours());

        assertEquals(updateRequestItems.getLabors().get(0).getWorkCompletedAt(),
            maintenanceRequest.getLabors().get(0).getWorkCompletedAt());

        assertEquals(updateRequestItems.getLabors().get(0).getDescription(),
            maintenanceRequest.getLabors().get(0).getDescription());

        assertEquals(maintenanceRequest.getId(), maintenanceRequest.getBillables().get(0).getMaintenanceRequestId());
        assertEquals(updateRequestItems.getBillables().get(0).getRate(),
            maintenanceRequest.getBillables().get(0).getRate());

        assertEquals(updateRequestItems.getBillables().get(0).getQuantity(),
            maintenanceRequest.getBillables().get(0).getQuantity());

        assertEquals(updateRequestItems.getBillables().get(0).getDescription(),
            maintenanceRequest.getBillables().get(0).getDescription());
    }
}
