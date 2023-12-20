package org.rent.circle.maintenance.api.dto.maintenance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

public class LaborDtoTest {

    @Test
    public void LaborDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("workDate");

        // Act
        beanTester.testBean(LaborDto.class);

        // Assert
        // Test Excluded fields

        // Arrange
        LaborDto labor = new LaborDto();

        // Act
        labor.setWorkDate(LocalDateTime.now());

        // Assert
        assertNotNull(labor.getWorkDate());
    }
}
