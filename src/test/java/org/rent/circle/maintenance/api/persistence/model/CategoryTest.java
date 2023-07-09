package org.rent.circle.maintenance.api.persistence.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDateTime;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CategoryTest {

    @Test
    public void CategoryTest_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("createdAt");
        beanTester.addExcludedField("updatedAt");

        // Act
        beanTester.testBean(Category.class);

        // Assert

        // Test Excluded fields

        // Arrange
        Category category = new Category();

        // Act
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        // Assert
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
    }
}
