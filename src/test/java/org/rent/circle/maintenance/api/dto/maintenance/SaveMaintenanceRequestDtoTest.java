package org.rent.circle.maintenance.api.dto.maintenance;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SaveMaintenanceRequestDtoTest {

    @Test
    public void SaveMaintenanceRequestDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(SaveMaintenanceRequestDto.class);

        // Assert
    }
}
