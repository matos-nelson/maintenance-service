package org.rent.circle.maintenance.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.maintenance.api.dto.SaveMaintenanceRequestDto;
import org.rent.circle.maintenance.api.enums.Status;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;
import org.rent.circle.maintenance.api.persistence.repository.MaintenanceRequestRepository;
import org.rent.circle.maintenance.api.service.mapper.MaintenanceMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class MaintenanceService {

    private final MaintenanceRequestRepository maintenanceRequestRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Transactional
    public Long saveRequest(SaveMaintenanceRequestDto saveRequest) {

        MaintenanceRequest maintenanceRequest = maintenanceMapper.toModel(saveRequest);

        maintenanceRequest.setStatus(Status.IN_PROGRESS);
        maintenanceRequestRepository.persist(maintenanceRequest);

        return maintenanceRequest.getId();
    }
}
