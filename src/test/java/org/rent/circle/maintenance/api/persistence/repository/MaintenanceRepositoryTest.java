package org.rent.circle.maintenance.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.persistence.model.MaintenanceRequest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class MaintenanceRepositoryTest {

    @Inject
    MaintenanceRequestRepository maintenanceRequestRepository;

    @Test
    @TestTransaction
    public void findByIdAndOwnerId_WhenMaintenanceRequestDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        MaintenanceRequest result = maintenanceRequestRepository.findByIdAndOwnerId(123L, 456L);

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findByIdAndOwnerId_WhenCalled_ShouldReturnMaintenanceRequest() {
        // Arrange

        // Act
        MaintenanceRequest result = maintenanceRequestRepository.findByIdAndOwnerId(200L, 1L);

        // Assert
        assertNull(result);
    }
}
