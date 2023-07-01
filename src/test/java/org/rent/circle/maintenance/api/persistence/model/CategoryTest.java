package org.rent.circle.maintenance.api.persistence.model;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CategoryTest {

    @Test
    public void CategoryTest_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(Category.class);

        // Assert
    }
}
