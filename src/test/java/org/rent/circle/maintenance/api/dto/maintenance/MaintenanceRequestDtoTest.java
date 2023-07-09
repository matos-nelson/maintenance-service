package org.rent.circle.maintenance.api.dto.maintenance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDateTime;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MaintenanceRequestDtoTest {

    @Test
    public void MaintenanceRequestDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("completedAt");
        beanTester.addExcludedField("updatedAt");

        // Act
        beanTester.testBean(MaintenanceRequestDto.class);

        // Assert
        // Test Excluded fields

        // Arrange
        MaintenanceRequestDto maintenanceRequest = new MaintenanceRequestDto();

        // Act
        maintenanceRequest.setCompletedAt(LocalDateTime.now());
        maintenanceRequest.setUpdatedAt(LocalDateTime.now());

        // Assert
        assertNotNull(maintenanceRequest.getCompletedAt());
        assertNotNull(maintenanceRequest.getUpdatedAt());
    }
}
