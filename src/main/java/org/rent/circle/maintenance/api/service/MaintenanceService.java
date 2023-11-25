package org.rent.circle.maintenance.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.maintenance.api.dto.maintenance.MaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.dto.maintenance.UpdateMaintenanceRequestDto;
import org.rent.circle.maintenance.api.enums.Status;
import org.rent.circle.maintenance.api.persistence.model.Category;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;
import org.rent.circle.maintenance.api.persistence.repository.CategoryRepository;
import org.rent.circle.maintenance.api.persistence.repository.MaintenanceRequestRepository;
import org.rent.circle.maintenance.api.service.mapper.MaintenanceMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class MaintenanceService {

    private final MaintenanceRequestRepository maintenanceRequestRepository;
    private final CategoryRepository categoryRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Transactional
    public Long saveRequest(SaveMaintenanceRequestDto saveRequest, @NotBlank String managerId) {

        Category category = categoryRepository.findById(saveRequest.getCategoryId());
        if (category == null) {
            log.info("Could Not Find Category With Given Id: {}", saveRequest.getCategoryId());
            return null;
        }

        MaintenanceRequest maintenanceRequest = maintenanceMapper.toModel(saveRequest);

        maintenanceRequest.setManagerId(managerId);
        maintenanceRequest.setCategory(category);
        maintenanceRequest.setStatus(Status.IN_PROGRESS.value);
        maintenanceRequestRepository.persist(maintenanceRequest);

        return maintenanceRequest.getId();
    }

    @Transactional
    public MaintenanceRequestDto updateRequest(UpdateMaintenanceRequestDto updateRequest, @NotBlank String managerId) {
        MaintenanceRequest maintenanceRequestDb = maintenanceRequestRepository.findByIdAndManagerId(
            updateRequest.getMaintenanceRequestId(), managerId);

        if (maintenanceRequestDb == null) {
            log.info("Could Not Find Maintenance Request With Given Id: {}", updateRequest.getMaintenanceRequestId());
            return null;
        }

        if (!Status.IN_PROGRESS.value.equals(maintenanceRequestDb.getStatus())) {
            log.info("Update Is Not Valid For Given Maintenance Request Id: {}",
                updateRequest.getMaintenanceRequestId());
            return null;
        }

        if (Status.COMPLETED.equals(updateRequest.getStatus())) {
            maintenanceRequestDb.setCompletedAt(LocalDateTime.now());
        }

        maintenanceRequestDb.setStatus(updateRequest.getStatus().value);
        maintenanceRequestDb.setNote(updateRequest.getNote());
        maintenanceRequestRepository.persist(maintenanceRequestDb);

        return maintenanceMapper.toDto(maintenanceRequestDb);
    }

    public MaintenanceRequestDto getRequest(Long maintenanceRequestId, @NotBlank String managerId) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository
            .findByIdAndManagerId(maintenanceRequestId, managerId);
        return maintenanceMapper.toDto(maintenanceRequest);
    }

    public List<MaintenanceRequestDto> getRequests(@NotBlank String managerId, int page, int pageSize) {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository
            .findMaintenanceRequests(managerId, page, pageSize);
        return maintenanceMapper.toDtoList(maintenanceRequests);
    }
}
