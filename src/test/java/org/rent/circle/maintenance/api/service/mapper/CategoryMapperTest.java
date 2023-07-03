package org.rent.circle.maintenance.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.maintenance.api.dto.category.CategoryDto;
import org.rent.circle.maintenance.api.persistence.model.Category;

@QuarkusTest
public class CategoryMapperTest {

    @Inject
    CategoryMapper categoryMapper;

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        CategoryDto result = categoryMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAValidCategory_ShouldReturnDto() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("category");

        // Act
        CategoryDto result = categoryMapper.toDto(category);

        // Assert
        assertNotNull(result);
        assertEquals(category.getId(), result.getId());
        assertEquals(category.getName(), result.getName());
    }

    @Test
    public void toDtoList_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        List<CategoryDto> result = categoryMapper.toDtoList(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDtoList_WhenGivenAValidList_ShouldReturnMappedList() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Category");

        // Act
        List<CategoryDto> result = categoryMapper.toDtoList(Collections.singletonList(category));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(category.getId(), result.get(0).getId());
        assertEquals(category.getName(), result.get(0).getName());
    }
}
