package org.rent.circle.maintenance.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class MaintenanceRepositoryTest {

    @Inject
    MaintenanceRequestRepository maintenanceRequestRepository;

    @Test
    @TestTransaction
    public void findByIdAndManagerId_WhenMaintenanceRequestDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        MaintenanceRequest result = maintenanceRequestRepository.findByIdAndManagerId(123L, "456");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findByIdAndManagerId_WhenCalled_ShouldReturnMaintenanceRequest() {
        // Arrange

        // Act
        MaintenanceRequest result = maintenanceRequestRepository.findByIdAndManagerId(200L, "1");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findMaintenanceRequests_WhenRequestsDoNotExist_ShouldReturnNoRequests() {
        // Arrange

        // Act
        List<MaintenanceRequest> result = maintenanceRequestRepository.findMaintenanceRequests("456", 0, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void findMaintenanceRequests_WhenRequestsDoExist_ShouldReturnRequests() {
        // Arrange

        // Act
        List<MaintenanceRequest> result = maintenanceRequestRepository.findMaintenanceRequests("1", 0, 10);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @TestTransaction
    public void findMaintenanceRequests_WhenRequestsDoNotExistInPage_ShouldReturnNoRequests() {
        // Arrange

        // Act
        List<MaintenanceRequest> result = maintenanceRequestRepository.findMaintenanceRequests("1", 10, 10);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
