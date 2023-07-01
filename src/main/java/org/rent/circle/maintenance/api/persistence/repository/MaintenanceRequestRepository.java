package org.rent.circle.maintenance.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@ApplicationScoped
public class MaintenanceRequestRepository implements PanacheRepository<MaintenanceRequest> {

}
