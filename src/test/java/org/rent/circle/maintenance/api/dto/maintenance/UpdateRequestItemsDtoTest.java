package org.rent.circle.maintenance.api.dto.maintenance;

import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

public class UpdateRequestItemsDtoTest {

    @Test
    public void UpdateRequestItemsDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(UpdateRequestItemsDto.class);

        // Assert
    }
}
