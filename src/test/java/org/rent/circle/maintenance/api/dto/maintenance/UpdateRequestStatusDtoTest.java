package org.rent.circle.maintenance.api.dto.maintenance;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateRequestStatusDtoTest {

    @Test
    public void UpdateRequestStatusDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(UpdateRequestStatusDto.class);

        // Assert
    }
}
