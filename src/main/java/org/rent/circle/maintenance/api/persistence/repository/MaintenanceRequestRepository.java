package org.rent.circle.maintenance.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@ApplicationScoped
public class MaintenanceRequestRepository implements PanacheRepository<MaintenanceRequest> {

    public MaintenanceRequest findByIdAndManagerId(Long id, String managerId) {
        Parameters queryParams = Parameters.with("id", id).and("managerId", managerId);
        return find("id = :id and managerId = :managerId", queryParams).firstResult();
    }

    public List<MaintenanceRequest> findMaintenanceRequests(String managerId, int page, int pageSize) {
        return find("managerId", managerId)
            .page(Page.of(page, pageSize))
            .list();
    }
}
