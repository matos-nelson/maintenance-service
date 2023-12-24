package org.rent.circle.maintenance.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDateTime;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class LaborTest {

    @Test
    public void Labor_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("workCompletedAt");
        beanTester.addExcludedField("createdAt");
        beanTester.addExcludedField("updatedAt");

        // Act
        beanTester.testBean(Labor.class);

        // Assert

        // Test Excluded fields

        // Arrange
        Labor labor = new Labor();

        // Act
        labor.setWorkCompletedAt(LocalDateTime.now());
        labor.setUpdatedAt(LocalDateTime.now());
        labor.setCreatedAt(LocalDateTime.now());

        // Assert
        assertNotNull(labor.getWorkCompletedAt());
        assertNotNull(labor.getCreatedAt());
        assertNotNull(labor.getUpdatedAt());
    }
}
