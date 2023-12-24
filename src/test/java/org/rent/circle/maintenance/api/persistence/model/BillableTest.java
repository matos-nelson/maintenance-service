package org.rent.circle.maintenance.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDateTime;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class BillableTest {

    @Test
    public void Billable_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("createdAt");
        beanTester.addExcludedField("updatedAt");

        // Act
        beanTester.testBean(Billable.class);

        // Assert

        // Test Excluded fields

        // Arrange
        Billable billable = new Billable();

        // Act
        billable.setUpdatedAt(LocalDateTime.now());
        billable.setCreatedAt(LocalDateTime.now());

        // Assert
        assertNotNull(billable.getCreatedAt());
        assertNotNull(billable.getUpdatedAt());
    }
}
