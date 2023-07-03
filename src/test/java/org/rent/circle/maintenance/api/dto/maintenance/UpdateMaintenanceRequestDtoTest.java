package org.rent.circle.maintenance.api.dto.maintenance;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateMaintenanceRequestDtoTest {

    @Test
    public void UpdateMaintenanceRequestDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(UpdateMaintenanceRequestDto.class);

        // Assert
    }
}
