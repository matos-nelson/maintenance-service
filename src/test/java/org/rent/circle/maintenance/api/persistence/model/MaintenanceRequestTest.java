package org.rent.circle.maintenance.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDateTime;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MaintenanceRequestTest {

    @Test
    public void MaintenanceRequest_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("completedAt");

        // Act
        beanTester.testBean(MaintenanceRequest.class);

        // Assert

        // Test Excluded fields

        // Arrange
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();

        // Act
        maintenanceRequest.setCompletedAt(LocalDateTime.now());

        // Assert
        assertNotNull(maintenanceRequest.getCompletedAt());
    }
}
