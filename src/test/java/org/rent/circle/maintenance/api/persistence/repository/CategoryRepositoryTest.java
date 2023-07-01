package org.rent.circle.maintenance.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.persistence.model.Category;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class CategoryRepositoryTest {

    @Inject
    CategoryRepository categoryRepository;

    @Test
    @TestTransaction
    public void listAll_WhenCalled_ShouldReturnCategories() {
        // Arrange

        // Act
        List<Category> results = categoryRepository.listAll();

        // Assert
        assertFalse(results.isEmpty());
    }
}
