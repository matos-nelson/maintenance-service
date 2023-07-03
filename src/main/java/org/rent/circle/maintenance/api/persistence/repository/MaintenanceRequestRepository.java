package org.rent.circle.maintenance.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@ApplicationScoped
public class MaintenanceRequestRepository implements PanacheRepository<MaintenanceRequest> {

    public MaintenanceRequest findByIdAndOwnerId(Long id, Long ownerId) {
        Parameters queryParams = Parameters.with("id", id).and("ownerId", ownerId);
        return find("id = :id and ownerId = :ownerId", queryParams).firstResult();
    }

    public List<MaintenanceRequest> findMaintenanceRequests(Long ownerId, int page, int pageSize) {
        return find("ownerId", ownerId)
            .page(Page.of(page, pageSize))
            .list();
    }
}
