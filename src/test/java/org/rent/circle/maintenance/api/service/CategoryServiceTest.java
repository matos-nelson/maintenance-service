package org.rent.circle.maintenance.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;
import org.rent.circle.maintenance.api.persistence.model.Category;
import org.rent.circle.maintenance.api.persistence.repository.CategoryRepository;
import org.rent.circle.maintenance.api.service.mapper.CategoryMapper;

@QuarkusTest
public class CategoryServiceTest {

    @InjectMock
    CategoryRepository categoryRepository;

    @Inject
    CategoryMapper categoryMapper;

    @Inject
    CategoryService categoryService;

    @Test
    public void getCategories_WhenCalled_ShouldReturnCategories() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("category");

        when(categoryRepository.listAll()).thenReturn(Collections.singletonList(category));

        // Act
        List<CategoryDto> result = categoryService.getCategories();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(category.getId(), result.get(0).getId());
        assertEquals(category.getName(), result.get(0).getName());
    }
}
